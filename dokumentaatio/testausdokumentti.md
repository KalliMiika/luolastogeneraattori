# Testausdokumentti

### JUnit testien 
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
