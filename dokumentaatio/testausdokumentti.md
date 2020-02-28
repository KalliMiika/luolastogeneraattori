# Testausdokumentti

### JUnit testien 
*Point-, Cave- ja Room -Olioille on tehty kohtalaisen kattavat JUnit testit,
projektin muut luokat on testattu lähinnä manuaalisesti*

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

## Luolastongenerointi algoritmien testailu
*Testailu on tehty manuaalisesti, koska en ole vielä keksinyt fiksua tapaa toteuttaa testejä ohjelmallisesti*

### RandomWalk

RandomWalk algoritmiä on tullut lähinnä testailtua niin, että sen on laittanut pyörähtämään main metodissa 
joka kerta kun ohjelman käynnistyksen yhteydessä ja tulostamaan tuloksensa ulos. Algoritmi ei mielestäni generoi
mitään edes etäisesti mielenkiintoista, joten tässä kohtaa testaus on keskittynyt vain siihen, että onko siellä joku potentiaalisesti
crashin aiheuttava bugi vai ei.

### Cellular Automata
Tällä algoritmillä on tullu leikittyä aika paljon projektin aikana. Parametrien vaihtelu vaikuttaa lopputulokseen todella paljon,
ja eri kokoisilla kartoilla haluaa käyttää eri parametrejä.
```
Käytetyt parametrit:
Cave:
  WIDTH=42    Ja  WIDTH = 64
  HEIGHT=21   Ja  HEIGHT = 32

Cellular Automata:
P(Wall) = Alustuksessa todennäköisyys jolla ruutu on seinä
R1      = Ensimmäinen seinien etsinnän etäisyys
C1      = Etsittävien seinien määrä ehdon toteutumiseen
R2      = Toinen seinien etsinnän etäisyys
C2      = Etsittävien seinien määrä ehdon toteutumiseen
```

|Step|P(Wall)|R1|C1|R2|C2|
|---|---|---|---|---|---|
|0|40|||||
|1||1|5|0|0|
|2||1|5|0|0|
|3||1|5|0|0|
|4||1|5|3|4|
|5||1|5|0|0|
|6||1|5|0|0|
|7||1|5|0|0|
|8||1|5|3|4|
|1||1|5|0|0|
|2||1|5|0|0|
|3||1|5|0|0|
|4||1|5|3|4|
|5||1|5|0|0|
|6||1|5|0|0|
|7||1|5|0|0|

<img src="https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/images/cellularautomatasmall.png"/>    <img src="https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/images/cellularautomatabig.png"/>

### Toistaiseksi nimeämätön Algoritmi

Algoritmi on vielä alkutekijöissään, mutta huoneiden erottelun testailua varten tein pari metodia jotka visualisoivat tapahtumien kulkua erottelusimulaation aikana 

Delaunay -Algoritmin testausta varten tein Graph.java luokan joka piirtää generoidun verkon

```
Käytetyt Parametrit:
Cave:
  WIDTH=120
  HEIGHT=60
Cave.generateUsingCaveGenerator():
  roomsToGenerate=30
```
- Ennen simulaatiota
<img src="https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/images/cavegeneratorInput.png"/>

- Simulaation jälkeen
<img src="https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/images/cavegeneratoroutput.png"/>

```
Käytetyt Parametrit:
Cave:
  WIDTH=1800
  HEIGHT=1200
Cave.generateUsingCaveGenerator():
  roomsToGenerate=75
Graph:
  kuvanskaalauskeroimiksi *5, defaulttina käyttänyt *15
```
- Delaunay -Algoritmin generoima verkko
<img src="https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/images/delaunay.png"/>

```
Käytetyt Parametrit:
Cave:
  WIDTH=88
  HEIGHT=44
Cave.generateUsingCaveGenerator():
  roomsToGenerate=12
CaveGenerator:
  Virittävä puu = new SpanningTrees().basic(rooms, corridors);
```
<img src="https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/images/virittavaPuuBasic.png"/>

```
Käytetyt Parametrit:
Cave:
  WIDTH=88
  HEIGHT=44
Cave.generateUsingCaveGenerator():
  roomsToGenerate=12
CaveGenerator:
  Virittävä puu = new SpanningTrees().random(rooms, corridors);
```
<img src="https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/images/virittavapuuRandom.png"/>
