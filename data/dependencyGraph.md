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
  :app([<a href='https://github.com/rjspies/Daedalus/blob/main/app/dependencyGraph.md' style='text-decoration:auto'>:app</a>]):::andNode;
  :data[<a href='https://github.com/rjspies/Daedalus/blob/main/data/dependencyGraph.md' style='text-decoration:auto'>:data</a>]:::andNode;
end

%% Dependencies

%% Dependents
:app-.->:data
```