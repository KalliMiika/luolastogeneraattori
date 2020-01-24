# Projektin määrittely

~~Tavoite on rakentaa ohjelma joka pystyy generoimaan satunnaisia 2D luolastoja, jotka koostuvat useista huoneista ja huoneita yhdistävistä käytävistä.~~

Tavoite on rakentaa erilaisia 2D luolastogeneraatio algoritmeja ja vertailla niiden ominaisuuksia (vähän auki vielä että mikä niissä olisi mielekästä vertailtavaa käytännössä)

## ~~Toteutettava algoritmi~~

~~Luolasto olisi tarkoitus luoda siten, että ensin luodaan suuri määrä satunnaisen kokoisia huoneita satunnaisiin paikkoihin kentällä, jonka jälkeen ajetaan simulaatio, jossa huoneet tönivät toisiaan törmätessään jolla saadaan levitettyä huoneet ympäri kenttää, siten ettei mitkään 2 huonetta ole päällekkäin. Näistä huoneista sitten valitaan sopiva määrä huoneita satunnaisesti joiden välille generoidaan käytäviä.~~

## Toteutettavat algoritmit

### RandomWalk

```
Annetaan 2D kartta joka koostuu ainoastaan seinistä, 
aloitetaan kaivaminen satunnaisesta kohtaa karttaa 
ja kaivetaan n määrä satunnaisen pituisia käytäviä satunnaisiin suuntiin
```

### Cellular Automata

```
Täytetään kartta satunnaisesti seinä ja lattiapaloista, 
sitten luodaan toistuvasti uusia karttoja niin, että 
jos solu oli seinä ja 4 tai useampi sitä ympäröivä 
solu olivat myös seiniä, niin solusta tulee seinä tai
jos solu ei ollut seinä, mutta 5 tai useampi sitä ympäröivistä
soluista olivat seiniä, niin solusta tulee seinä.
```

### Sekä ainakin toinen seuraavista

#### Muokattu versio TinyKeep pelin luolastogenerointi algoritmista
```
Luolasto olisi tarkoitus luoda siten, että ensin luodaan suuri
määrä satunnaisen kokoisia huoneita satunnaisiin paikkoihin
kentällä, jonka jälkeen ajetaan simulaatio, jossa huoneet tönivät 
toisiaan törmätessään jolla saadaan levitettyä huoneet ympäri kenttää,
siten ettei mitkään 2 huonetta ole päällekkäin. Näistä huoneista sitten 
valitaan sopiva määrä huoneita satunnaisesti joiden välille generoidaan käytäviä.
```
#### Vähän muokattu versio ylemmästä
```
Luodaan satunnainen, kuitenkin suhteellisen pieni määrä huoneita, 
niin ettei minkään kahden huoneen keskipisteet ole päällekkäin.
Asetetaan jokaiselle huoneelle "kupla" jonka sisälle huone mahtuu kokonaan.
Liikutellaan huoneita niin, että minkään kahden huoneen kuplat eivät leikkaa toisiaan.
Tämän jälkeen käytetään Delaunay triangulation:a jolla saadaan kaikki mahdolliset
käytävät huoneiden välille, niin ettei mitkään 2 käytävää leikkaa toisiaan.
Selvitetään saadun verkon pienin virittävä puu jolla saadaan karsittua ylimääräisiä
Käytäviä pois, mutta kuitenki niin että jokaiseen huoneeseen on mahdollista päästä.
Käytävien generoinnista en ole vielä varma miten se tulisi toimimaan.
```
## ~~Mahdollisia laajennusideoita algoritmille~~

~~Vierekkäisten huoneiden yhdisteleminen mielenkiintoisempien muotoisten huoneiden luomiseksi.~~

## Lähteet

- https://www.freecodecamp.org/news/how-to-make-your-own-procedural-dungeon-map-generator-using-the-random-walk-algorithm-e0085c8aa9a/
- http://roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels
- https://www.gamasutra.com/blogs/AAdonaac/20150903/252889/Procedural_Dungeon_Generation_Algorithm.php
- https://www.reddit.com/r/gamedev/comments/1dlwc4/procedural_dungeon_generation_algorithm_explained/
- http://pcg.wikidot.com/pcg-algorithm:dungeon-generation

