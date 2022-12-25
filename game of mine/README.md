# TP DEMINEUR

#### Ismail CHAF-I

Le plateau est divisé en cases, avec des mines placées aléatoirement. Pour gagner, vous devez ouvrir toutes les cases qui ne contiennent pas de mines. Cliquer sur une case qui n’a pas de mine révélera un nombre. Ce nombre correspond au nombre de mines adjacentes à cette case. À l’aide de ces informations, vous pouvez déterminer les cases sûres et les cases contenant des mines.


### HowTo

**Compiler les classes :**

```console
$ make compile
```

**Exécuter le jeu :**

```console
$ make play
```

**Compiler les tests :**

```console
$ make tests
```
**Exécuter un test, exemple MatriceBombTest :**

```console
$ java -jar test4poo.jar demineur.matrice.MatriceBombTest

```

**Supprimer les .class :**

```console
$ make clean
```

