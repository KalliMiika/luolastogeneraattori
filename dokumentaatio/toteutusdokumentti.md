# Toteutusdokumentti

## Rakenne

Projektissa vertaillaan erilaisia luolastongenerointi algoritmeja. 
Näistä on tähän mennessä toteutettu kaksi, RandomWalk ja CellularAutomata. 
Sen lisäksi työn alla on [Tässä blogipostissa](https://www.gamasutra.com/blogs/AAdonaac/20150903/252889/Procedural_Dungeon_Generation_Algorithm.php)
kuvaillun algoritmin kaltainen hieman muunneltu algoritmi.

### RandomWalk
#### Muuttujat
|Nimi|Tyyppi|Kuvaus|
|---|---|---|
|maxTunnels|int|Kaivettavien tunneleiden lukumäärä|
|maxTunnelLength|int|Kaivettavien tunneleiden maksimipituus|
|minTunnelLength|int|Kaivettavien tunneleiden minimipituus|
#### Toiminta
```
1. Generoidaan kartta, joka koostuu ainoastaan seinistä.
2. Randomoidaan piste kartalta
3. Muutetaan aloituskohdasta seinä lattiaksi
4. Loopataan 0 -> maxTunnels
  5. Valitaan satunnainen numero väliltä minTunnelLength ~ maxTunnelLength
  6. Valitaan satunnainen suunta, kuitenkin sillä rajotuksella, että jos viimeksi kaivettiin vertikaalisuuntaan
  niin nyt kaivetaan horisontaali suuntaan ja päinvastoin
  7. Kaivetaan valittuun suuntaan valittu määrä askelia muuttaen seiniä lattiaksi joka askeleella.
8. Kun tunneleita on kaivettu maxTunnels verran, ohjelma on valmis.
```
#### Aika- Ja Tilavaativuudet
```
Tyhjän kartan luomisessa jokaisessa solussa vieraillaan kerran, O(n) aikaa ja O(n) tilaa.
Tunneleita kaivetaan n määrä m pituisia tunneleita,  O(n*m) aikaa ja O(1) tilaa.
```
#### Puutteita Ja Parannusehdotuksia
```
Pähkinänkuoressa, mielestäni RandomWalk algoritmin toteuttamat luolastot ovat surkeita, 
enkä keksi että miten sitä voisi muokata niin, että se yhä olisi "RandomWalk", 
mutta silti toteuttaisi fiksun näköistä jälkeä.

Aion kuitenkin kokeilla, että soveltuisiko RandomWalk esimerkiksi 
käytävien generoimiseen huoneiden välille pienellä viilaamisella.
```

### Cellular Automata
#### Muuttujat
|Nimi|Tyyppi|Kuvaus|
|---|---|---|
|map|char[][]|Generointiin käytettävä pohja|
|wallSearchRange1|int|Etäisyys jonka säteeltä etsitään seiniä|
|wallCutOff1|int|Raja jolla ensimmäinen leikkaus suoritetaan|
|wallSearchRange2|int|Etäisyys jonka säteeltä etsitään seiniä|
|wallCutOff2|int|Raja jolla toinen leikkaus suoritetaan|
#Toiminta
```
1. Generoidaan pohja jossa jokaiselle kartan solulle arvotaan jollain todennäköisyydellä, 
2. Alustetaan output kartta (char[][] result)
  että tuleeko siihen seinä vai lattia.
3. Käydään koko parametrina saatu kartta (char[][] map) läpi ruutu ruudulta
  5. Lasketaan wallSearchRange1 säteellä olevien seinien lukumäärä ruudusta
  6. Lasketaan wallSearchRange2 säteellä olevien seinien lukumäärä ruudusta
  7. Jos Ensimmäisen etsinnän tulos oli > wallCutOff1 TAI Toisen etsinnän tulos oli <= wallCutOff2,
    Asetetaan output kartan(char[][] result) vastaavaan ruutuun SEINÄ, muutoin asetetaan ruutuun LATTIA
8. Kun kaikki ruudut on käyty lävitse, palautetaan output kartta(char[][] result) ohjelman tuloksena.
Steppejä 2-8 voidaan suorittaa monta kertaa, vaihtaen parametrejä kertojen välillä. Edellisen kierroksen
outputtia käytetään seuraavan kierroksen inputtina. Tällöin saadaan hyvin erilaisia luolastoja aikaiseksi.
```
#### Aika- Ja Tilavaativuudet
```
Pohjana käytettävän kartan luomisessa jokaisessa solussa vieraillaan kerran, O(n) aikaa ja O(n) tilaa.
Algoritmi käy jokaisessa ruudussa kerran ja tutkii sitä ympäröiviä ruutuja, O(n*log(n)) aikaa ja O(n) tilaa
```
#### Puutteita Ja Parannusehdotuksia
```
Cellular Automata generoi usein luolastoja jotka eivät ole yhtenäisiä,
eli aina ei löydy reittiä kahden eri pisteen väliltä. Tähän kuitenkin
löytyy joitakin korjaus ideoita. 
Lupaavin näistä on Floodfill, jonka idea on, että valitaan random piste kartalta,
joka on lattiapala ja suoritetaan siitä leveyshaku niin, että merkataan
kaikki vieraillut lattiapalat. Tämän jälkeen käydään kartta läpi ja muutetaan 
kaikki merkkaamattomat lattiapalat seinäpaloiksi. Tällöin jäljelle jäävä luolasto
on yhtenäinen.
```

### TBD
Algoritmi jolle en oikeastaan muistanut keksiä nimeä
#### Muuttujat
|Nimi|Tyyppi|Kuvaus|
|---|---|---|
|roomsToGenerate|int|Generoitavien huoneiden lukumäärä|
|collisionMethod|String|Törmäystarkistukseen käytettävä metodi|
|spanningTreeType|String|Virittävän Puun muodostukseen käytettävä metodi|
|treeCutOff|int|Todennäköisyys, jolla puuhun kuulumaton käytävä otetaan mukaan|
#### Toiminta
```
1. Aloitetaan generoimalla parametrina annetun roomsToGenerate verran huoneita.
2. Ajetaan simulaatio jossa huoneet kimpoilevat seinistä ja toisistaan törmätessään,
  kunnes törmäilyjä ei enään tapahdu. Oletetaan, että tällöin yksikään huone ei
  ole toisen huoneen päällä tai osittain seinän sisällä.
3. Muodostetaan generoiduista huoneista verkko Delaunay Triangulation algoritmilla
4. Muodostetaan generoidusta verkosta virittävä puu
5. generoidaan käytävät a* haulla
```
#### Aika- Ja Tilavaativuudet
```
n = generoitavien huoneiden lukumäärä
Huoneiden generointi O(n) aika
Huoneiden törmäilyä en osaa arvioida, testailun perusteella luokkaa 
  SQUARE:lla O(n^2 * log(n))
  SPHERE:llä O(n^2 * log(n))
Delaunay Triangulation Jokainen huone käydään läpi kerran, jokaista huonetta kohtaan tarkistetaan 
  kolmioita, joita on sitä enemmän, mitä enemmän huoneita on, arvio O(n * log(n))
Virittäväpuu
  Delaunay "pahin mahdollinen" case on, että 3 huonetta = 3 käytävää, jonka jälkeen aina, kun 
  lisätään uusi huone, käytävien määrä kasvaa 3:lla. 
  RANDOM käy kaikki käytävät läpi satunnaisessa järjestyksessä, käytävien lukumäärä on 
  pahimmillaan 3*n, uuden huoneen löytyessä käydään jokainen käytävä läpi ja tutkitaan, 
  että onko se kiinni tarkasteltavassa huoneessa, aikavaativuus on siis O(3 * n^2)
  
  BASIC Ottaa ensimmäisen huoneen listasta, etsii käytävät jotka ovat liitoksissa siihen, 
  ja tutkii sitten käytävien toisessa päässä olevia huoneita. Jokaisessa huoneessa vieraillaan
  kerran, ja kaikki käytävät tutkitaan joka huoneen kohalla aikavaativuus O(3 * n^2)
Käytävien Kaiverrus
  Käytäviä on väliltä n~3n, parametrin treeCutOff(0~100) mukaan.
  x = kartan leveys
  y = kartan korkeus
  käytävät kaiverretaan A* haulla päästä päähän, kulmittain liikkuminen ei ole sallittua
  Askelia tehdään luokkaa x+y, joka askeleella tutkitaan 4 ympäröivää ruutua, 
  aikavaativuus noin O(12 * n(x + y))
  
Aikavaativuus muistaakseni merkittiin kokonaisuuden suurimman aikasyöpön mukaan, 
eli se olisi törmäystarkistuksen O(n^2*log(n))
```
#### Puutteita Ja Parannusehdotuksia
```
Delaunay Triangulation Algoritmiin jäi jokin bugi, jonka takia se välillä generoi käytäviä, 
jotka leikkaavat toisia käytäviä, vaikka se ei ole haluttu ominaisuus.
Jossain kohtaa välillä Delaunay Triangulation ja Spanning Tree tapahtuu bugi, 
jonka lopputuloksena generoituu huone johon ei johda ainuttakaan käytävää, 
en onnistunut paikantamaan bugia.
```

### TinyKeepIsh
Tarkoitus oli koittaa tehdä oma versio [tästä](https://www.gamasutra.com/blogs/AAdonaac/20150903/252889/Procedural_Dungeon_Generation_Algorithm.php) kuitenkin niin, että mitään lähdekoodia tms en lukenut vaan luin pääpiirteittäin idean ja koitin keksiä toteutukset itse. Delaunay Triangulationin kanssa tosin jouduin turvautumaan jonkun toisen laskutoimitukseen kolmiota ympäröivän ympyrän laskemiseen kun en itse osannut ja aika meinasi loppua kesken

#### Muuttujat
|Nimi|Tyyppi|Kuvaus|
|---|---|---|
|roomsToGenerate|int|Generoitavien huoneiden lukumäärä|
|generationMethod|String|Huoneiden generointi patterni|
|collisionMethod|String|Törmäystarkistukseen käytettävä metodi|
|largeCutoff|int|Minimi Pinta-ala, jolla huone luokitellaan isoksi|
|spanningTreeType|String|Virittävän Puun muodostukseen käytettävä metodi|
|treeCutOff|int|Todennäköisyys, jolla puuhun kuulumaton käytävä otetaan mukaan|
#### Toiminta
```
1. Generoidaan satunnaisia huoneita läjäpäin ruudulle
2. Ajetaan simulaatio jossa huoneet tönivät toisiaan kunnes mitkään kaksi huonetta eivät ole toistensa päällä
3. Generoidaan vekko jossa  huoneet ovat verkon solmuja Delaunay Triangulation Algoritmilla
4. Generoidaan luodusta verkosta virittävä puu
5. Täytetään tyhjä tila 1x1 kokosilla huoneilla
6. Generoidaan aputaulu josta selviää että minkä alueen mikäkin huone peittää kartalta
7. Piirretään ne huoneet, joiden yli virittävän puun määrittämät käytävät kulkevat linnuntietä
```
#### Aika- Ja Tilavaativuudet
```
n = generoitavien huoneiden lukumäärä
Huoneiden generointi O(n) aika
Huoneiden törmäilyä en osaa arvioida, testailun perusteella luokkaa 
  SQUARE:lla O(n^2 * log(n))
  SPHERE:llä O(n^2 * log(n))
Delaunay Triangulation Jokainen huone käydään läpi kerran, jokaista huonetta kohtaan tarkistetaan 
  kolmioita, joita on sitä enemmän, mitä enemmän huoneita on, arvio O(n * log(n))
Virittäväpuu
  Delaunay "pahin mahdollinen" case on, että 3 huonetta = 3 käytävää, jonka jälkeen aina, 
  kun lisätään uusi huone, käytävien määrä kasvaa 3:lla. 
  RANDOM käy kaikki käytävät läpi satunnaisessa järjestyksessä, käytävien lukumäärä on 
  pahimmillaan 3*n, uuden huoneen löytyessä käydään jokainen käytävä läpi ja tutkitaan, 
  että onko se kiinni tarkasteltavassa huoneessa, aikavaativuus on siis O(3 * n^2)
  
  BASIC Ottaa ensimmäisen huoneen listasta, etsii käytävät jotka ovat liitoksissa siihen, 
  ja tutkii sitten käytävien toisessa päässä olevia huoneita. Jokaisessa huoneessa vieraillaan kerran, 
  ja kaikki käytävät tutkitaan joka huoneen kohalla aikavaativuus O(3 * n^2)
1x1 kokoisten huoneiden generointi O(x*y), jossa x = kartan leveys y = kartan korkeus
Ruutujen numerointi O(x*y), jokaisessa ruudussa vieraillaan kerran
Huoneiden piirtäminen
  Edetään kohtisuoraa käytäviä pitkin, tarkasteltavia ruutuja maksimissaan luokkaa 
  käytävien lukumäärä * (x+y) eli O(3*n(x+y))
  
Suurin aikasyöppö sama kuin TBD:ssä, eli se olisi törmäystarkistuksen O(n^2*log(n))
```
#### Puutteita Ja Parannusehdotuksia
```
Delaunay Triangulation Algoritmiin jäi jokin bugi, jonka takia se välillä generoi käytäviä, 
jotka leikkaavat toisia käytäviä, vaikka se ei ole haluttu ominaisuus.
Jossain kohtaa välillä Delaunay Triangulation ja Spanning Tree tapahtuu bugi, 
jonka lopputuloksena generoituu huone johon ei johda ainuttakaan käytävää, 
en onnistunut paikantamaan bugia.

Lopputulos on aika nyrkillä näyttöön lyödyn näköinen, sitä voisi parantaa esim ympäröimällä 
huoneita seinillä ja laittaa jotain järkeä käytävien generointiin ettei kahta käytävää 
laiteta menemään käytännössä vierekkäin samaan suuntaan, 
jonka lopputuloksena on todella pasku ja epämääräinen mössö.
```
### Lähteet
- https://www.freecodecamp.org/news/how-to-make-your-own-procedural-dungeon-map-generator-using-the-random-walk-algorithm-e0085c8aa9a/
- http://roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels
- https://www.gamasutra.com/blogs/AAdonaac/20150903/252889/Procedural_Dungeon_Generation_Algorithm.php
- https://www.reddit.com/r/gamedev/comments/1dlwc4/procedural_dungeon_generation_algorithm_explained/
- http://pcg.wikidot.com/pcg-algorithm:dungeon-generation
- https://github.com/Yonaba/delaunay/blob/master/delaunay.lua
- https://www.geeksforgeeks.org/program-find-circumcenter-triangle-2/
- https://en.wikipedia.org/wiki/Bowyer%E2%80%93Watson_algorithm
- https://github.com/Bl4ckb0ne/delaunay-triangulation
