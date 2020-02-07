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

### Lähteet
- https://www.freecodecamp.org/news/how-to-make-your-own-procedural-dungeon-map-generator-using-the-random-walk-algorithm-e0085c8aa9a/
- http://roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels
- https://www.gamasutra.com/blogs/AAdonaac/20150903/252889/Procedural_Dungeon_Generation_Algorithm.php
- https://www.reddit.com/r/gamedev/comments/1dlwc4/procedural_dungeon_generation_algorithm_explained/
- http://pcg.wikidot.com/pcg-algorithm:dungeon-generation
