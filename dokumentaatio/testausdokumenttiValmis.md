## Valmiin ohjelman testausdokumentti

### Random Walk
```
RandomWalk vaikuttaisi Javan tarjoamilla työkaluilla aina väittävän vievänsä  käytännössä
0ms ja 0b aikaa / muistia riippumatta parametreistä, kartalta loppuu kaiveltavat ruudut,
ennen kun sen saa viemään yli 10ms aikaa
```

### CellularAutomata
```
Cellular Automatan aika arvio oli O(n * m * 9), n = Iteraatioiden lukumäärä, m = Kartan koko, 
testailun perusteella, kun iteraatioiden lukumäärää kasvatti, niin käytetty aika kasvoi aika 
lineaarisesti, tosin pienillä arvoilla, joilla aikaa menee esim kentän alustukseen, 
aikavaativuus oli huomattavasti suurempi suhteessa syötteen kokoon
Esimerkiksi 
iteraatioita       aika
  3               11~14ms
  30              59~63ms
  300            528~530ms
  3000           5147~5142ms
```

### Useissa Projekteissa Käytettyjä Algoritmeja
#### Törmäystarkistukset
```
Testailussa kävi aika nopeasti ilmi, että SPHERE törmäystarkistus levittää huoneet
noin 3x nopeammin kuin SQUARE. 
TinyKeepish:in Demo toiminnolla kuitenkin testasin, että SQUARE vaikuttaisi suoriutuvan pienemmällä
määrällä törmäystarkistus looppeja, eli olettaisin, että 4 pisteen tarkistaminen noin huomattavasti
enemmän aikaa kun kahden pisteen välisen etäisyyden laskeminen.

Algoritmien kokonaisaikavaativuudeksi oli arvioitu O(n^2 * log(n)) törmäystarkistuksien
perusteella. Tämä osottautui erittäin vääräksi kun alkoi testailemaan todella erilaisilla numeroilla

Generation : STACK
Collision : SQUARE
Large Cutoff : 4
Spanning Tree: BASIC
Tree CUtoff : 0
```
|n|aika|
|---|---|
|25|1 ms|
|100|10 ms|
|250|1300 ms|

en nyt keksinyt että miten tästä graafin piirtäisi mutta äkkisillään arvioituna näyttäisi olevan lähempänä
O(n^5 * log(n))

#### Virittävät Puut
```
TinyKeepish algoritmilla, kun generoi 400 huonetta ja piti muut parametrit samana, niin
virittävät puut vaikuttivat olevan suunnilleen yhtä nopeita, kuten arvioitukin.
```


