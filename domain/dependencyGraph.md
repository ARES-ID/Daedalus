```mermaid
%%{ init: { 'theme': 'base' } }%%
graph LR;

%% Styling for module nodes by type
classDef rootNode stroke-width:4px;
classDef mppNode fill:#ffd2b3,color:#333333;
classDef andNode fill:#baffc9,color:#333333;
classDef javaNode fill:#ffb3ba,color:#333333;

%% Modules
subgraph  
  direction TB;
  :domain[<a href='https://github.com/rjspies/Daedalus/blob/main/domain/dependencyGraph.md' style='text-decoration:auto'>:domain</a>]:::andNode;
end

%% Dependencies

%% Dependents
```