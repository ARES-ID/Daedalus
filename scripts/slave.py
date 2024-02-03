#!env /usr/bin/python3

import os as OperatingSystem
import sys as System
import re as RegularExpression
from git import Repo as GitRepository
from jira import JIRA as Jira
from github import Github
from github import Auth as GithubAuthentication

JIRA_TOKEN_ENVIRONMENT_VARIABLE = "SLAVE_JIRA"
JIRA_SERVER_ADDRESS = "https://aresid.atlassian.net"
JIRA_EMAIL_ADDRESS = "rene.j.spies@gmail.com"
JIRA_ISSUE_TYPES_STORY = ["developer story", "user story"]
JIRA_ISSUE_TYPE_BUG = "bug"
JIRA_ISSUE_TYPE_SPIKE = "spike"

GITHUB_TOKEN_ENVIRONMENT_VARIABLE = "SLAVE_GITHUB"
GITHUB_REPOSITORY_NAME = "rjspies/Daedalus"
GITHUB_ASSIGNEE_NAME = "rjspies"
GITHUB_PULL_REQUEST_BASE_BRANCH = "main"
GITHUB_PULL_REQUEST_DRAFT = True

GIT_RELATIVE_REPOSITORY_PATH = "/Projekte/Daedalus"
GIT_BRANCH_PREFIX_STORY = "Feature"
GIT_BRANCH_PREFIX_BUG = "Bugfix"
GIT_BRANCH_PREFIX_SPIKE = "Experimental"

def main(ticket_key: str):
	jira_token = OperatingSystem.environ[JIRA_TOKEN_ENVIRONMENT_VARIABLE]
	jira = Jira(
		server = JIRA_SERVER_ADDRESS,
		basic_auth = (JIRA_EMAIL_ADDRESS, jira_token)
	)

	try:
		issue = jira.issue(ticket_key, fields ="key,summary,issuetype")
	except:
		raise KeyError(f"Issue with key '{ticket_key}' has not been found")

	branch_name = get_branch_name_from_issue(issue)
	create_branch(branch_name)
	create_github_pull_request(issue, branch_name)


def create_github_pull_request(issue, branch_name: str):
	github_authentication_token = OperatingSystem.environ[GITHUB_TOKEN_ENVIRONMENT_VARIABLE]
	github_authentication = GithubAuthentication.Token(github_authentication_token)
	github = Github(auth = github_authentication)
	github_repository = github.get_repo(GITHUB_REPOSITORY_NAME)
	key = issue.key
	summary = issue.fields.summary
	pull_request_title = f"{key} {summary}"
	pull_request = github_repository.create_pull(base = GITHUB_PULL_REQUEST_BASE_BRANCH, head = branch_name, title = pull_request_title, draft = GITHUB_PULL_REQUEST_DRAFT)
	pull_request.add_to_assignees(GITHUB_ASSIGNEE_NAME)


def create_branch(branch_name):
	home_directory = OperatingSystem.environ["HOME"]
	repository = GitRepository(f"{home_directory}{GIT_RELATIVE_REPOSITORY_PATH}")
	repository.create_head(branch_name)
	repository.git.push("--set-upstream", "origin", branch_name)


def get_branch_name_from_issue(issue):
	prefix = get_prefix_from_issue_type(issue.fields.issuetype.name)
	summary = formatted_summary(issue.fields.summary)
	return f"{prefix}/{issue.key}+{summary}"


def formatted_summary(summary: str):
	cleaned_summary = summary.replace(" ", "_")
	pattern = r"[^a-zA-Z0-9\.\-_\/]"
	cleaned_summary = RegularExpression.sub(pattern, "", cleaned_summary)
	return cleaned_summary


def get_prefix_from_issue_type(name: str):
	internal_name = name.lower()
	if internal_name in JIRA_ISSUE_TYPES_STORY:
		return GIT_BRANCH_PREFIX_STORY
	elif internal_name == JIRA_ISSUE_TYPE_BUG:
		return GIT_BRANCH_PREFIX_BUG
	elif internal_name == JIRA_ISSUE_TYPE_SPIKE:
		return GIT_BRANCH_PREFIX_SPIKE
	else:
		raise TypeError(f"Unknown issue type '{internal_name}'")


if __name__ == "__main__":
	try:
		ticket_key = System.argv[1]
	except:
		raise AttributeError("First argument 'ticket_key' has not been set")
	main(ticket_key)