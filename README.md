# Spring Boot training

Ce projet regroupe des exercises pratiques créés dans le cadre d'une formation d'introduction à Spring et Spring Boot.

Les exercises se présentent sous la forme de tests JUnit qui échouent et qu'il faut donc faire passer.

## Le code

Le code utilisé pour ces exercices est un "Hello World" artificiellement complexifié.

Le cœur de ce code est formé de la classe `Greeter` et l'interface `GreetingProvider`. Cette interface est un SPI
utilisé par `Greeter`, dont les classes `Bonjour` et `Hello` sont des implémentations.

À ce cœur s'ajoutent des extensions qui apportent différentes fonctionnalités : configuration, persistence et api REST.

## Les exercices

Les exercices sont séparés en deux parties, la première sur les features de Spring Core (dans le package `p1_spring`) et
la deuxième sur les features de Spring Boot (dans le package `p2_boot`).

Il est conseillé d'exécuter tous les tests d'une partie (ou bien tous les tests) dans l'IDE, puis de les résoudre un par
un et dans l'ordre, mais en relançant tous les tests à chaque itération.