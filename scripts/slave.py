#!env /usr/bin/python3

import os as OperatingSystem
import sys as System
import re as RegularExpression
from git import Repo as GitRepository
from jira import JIRA as Jira

def main(ticket_key: str):
	jira_token = OperatingSystem.environ["TCHIMP_JIRA"]
	jira = Jira(
		server = "https://aresid.atlassian.net",
		basic_auth = ("rene.j.spies@gmail.com", jira_token)
	)

	try:
		issue = jira.issue(ticket_key, fields ="key,summary,issuetype")
	except:
		raise KeyError(f"Issue with key '{ticket_key}' has not been found")

	branch_name = create_branch_name_from_issue(issue)

	home_directory = OperatingSystem.environ["HOME"]
	repository = GitRepository(f"{home_directory}/Projekte/Daedalus")


def create_branch_name_from_issue(issue):
	prefix = get_prefix_from_issue_type(issue.fields.issuetype.name)
	summary = clean_summary(issue.fields.summary)
	return f"{prefix}/{issue.key}+{summary}"


def clean_summary(summary: str):
	cleaned_summary = summary.replace(" ", "_")
	pattern = r"[^a-zA-Z0-9\.\-_\/]"
	cleaned_summary = RegularExpression.sub(pattern, "", cleaned_summary)
	return cleaned_summary


def get_prefix_from_issue_type(name: str):
	internal_name = name.lower()
	if internal_name in ["developer story", "user story"]:
		return "Feature"
	elif internal_name == "bug":
		return "Bugfix"
	elif internal_name == "spike":
		return "Experimental"
	else:
		raise TypeError(f"Unknown issue type '{internal_name}'")


if __name__ == "__main__":
	try:
		ticket_key = System.argv[1]
	except:
		raise AttributeError("First argument 'ticket_key' has not been set")
	main(ticket_key)