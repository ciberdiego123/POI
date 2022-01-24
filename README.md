# Test technique Java

## Introduction

L'objectif de cet exercice est d'évaluer votre capacité à développer.

Le but est de calculer la densité de population à partir d'un fichier d'entrée.

## Exercice

À partir d'une structure de données en mémoire contenant des points d'intérêts (POIs) caractérisés par: `id`, `lat`
, `lon`), on veut calculer le nombre de POIs d'une zone et trouver les N zones les plus denses (ie. les zones contenant
le plus de POIs).

Pour simplifier l'exercice, on considèrera que le monde est une grille allant de -90 a 90 et de -180 a 180 par
incréments de 0,5.
**Une zone est donc une case de la grille.** Par exemple: Paris est dans la zone `[(49, 2), (48.5, 2.5)]`.

Écrire le code en Java, sans s'appuyer sur une base de données pour commencer. Intégrer ce code dans un CLI (command
line interface). En bonus,

- vous pourrez intégrer ce code dans un web-service (REST).
- vous pourriez ajouter une base de données
- vous pourriez ajouter du monitoring
- ou tout autre fonctionnalités qui vous semblerait pertinente

Gardez bien a l'esprit que le but **est d'avoir une application fonctionnelle**
La rédaction de test unitaires est plus que vivement encouragée, il vaut mieux se concentrer sur des bases solides
plutôt que de nomreuses features mal implémentée

## Instructions & Critères d'évaluation

Vous avez 48h pour effectuer le rendu de cet exercice, le rendu doit se faire sous forme d'un repo github. Le test
doit-être fait en Java, vous pouvez ensuite utiliser la stack technique que vous souhaitez (jetez un oeil a celle
d'Happn si besoin ;) )

Votre code sera apprécié sur plusieurs axes

- tests unitaires ;
- structure du code / architecture logiciel
- clarté / lisibilité du code ;
- bonne pratiques / méthodologie
- performance dans le calcul de la solution ;

N'hésitez pas à inclure un README avec toute information que vous pensez importante pour nous aider à comprendre votre
raisonnement.

## Exemples

Étant donné le fichier de données en entrée suivant :

```
@id  @lat   @lon
id1 -48.6  -37.7
id2 -27.1    8.4  
id3   6.6   -6.9  
id4  -2.3   38.3  
id5   6.8   -6.9  
id6  -2.5   38.3  
id7   0.1   -0.1  
id8  -2.1   38.1
```

* Pour la question "_Calculer le nombre de POIs d'une **zone**_" (avec `min_lat= 6.5` et `min_lon= -7`).

Pour l'appel suivant :
``` java com.happn.techtest.Main --nbpoi '{"min_lat": 6.5, "min_lon": -7}'```

Le résultat (au format json) attendu est le suivant :

   ```
   {
      "value": 2
   }
   ``` 

* Pour la question "_Trouver les **n** zones les plus denses_" (avec `n= 2`).

Pour l'appel suivant :
``` java com.happn.techtest.Main --densest '{"n": 2}'```

Le résultat attendu (au format json) est le suivant :

   ```
    [
       {
          "min_lat": -2.5,
          "max_lat": -2,
          "min_lon": 38,
          "max_lon": 38.5
       },
       {
          "min_lat": 6.5,
          "max_lat": 7,
          "min_lon": -7,
          "max_lon": -6.5
       }
    ]
   ```

GL & HF !
