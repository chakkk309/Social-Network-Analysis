### Find mutual friends and opinion leaders in social network

##### 1. Dataset

[Social circles: Twitter(SNAP)](https://snap.stanford.edu/data/ego-Twitter.html), friends follow relationship is a directed graph. 

##### 2. Software version

* Spark 2.1.0 + GraphX
* Scala 2.12
* optional: Hadoop and its ecosystem (HDFS, Yarn)
* optional DB: Neo4j / Nebular Graph

**3. Potential Graph Visualization Scenarios**

spark -> gexf file -> Import Gephi -> visualization

##### 4. Potential Algorithm for Finding Opinion Leaders

* BetweennessCentrality, ref [NetworkX](https://github.com/networkx/networkx/tree/main/networkx/algorithms/centrality) (implement by Python)
* PageRank

##### 5. Potential Algorithm for Finding mutual friends

* WIP





