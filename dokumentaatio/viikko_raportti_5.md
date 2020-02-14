# Viikon 5 Raportti

### Tällä Viikolla

#### Ohjelmointia

- Delaunay Triangulation -Algoritmi tehty valmiiksi
- Triangle -Olio lisätty ^ algoritmia varten
  - Metodi pointIsWithinCircumCircle(Point point), joka tarkistaa onko parametrina annettu Point kolmion CircumCirclen sisällä
  - Metodi getCircumCenter(), joka laskee sen ympyrän keskipisteen, jonka kehä kulkee kaikkien kolmion kulmien lävitse
  - Metodi getRadius(), Laskee ^ ympyrän säteen
  
#### Dokumentaatio
- JavaDoc kommentoinnit pidetty ajantasalla
- Testausdokumentti pidetty ajantasalla
- Toteutusdokumenttiin ei mitään lisättävää vielä

#### Testaus
- JUnit testit pidetty ajantasalla, generaatioalgoritmejä lukuunottamatta

#### Checkstyle
- Koodi pidetty suurinpiirtein määritellyn Checkstylen hyväksyttävänä

#### Epäselvää
Jostain syystä jokin osa testikattavuuden selvittämisestä lakkasi toimimasta, eli jos generoin raportin jacoco:lla, niin se näyttää
että yhtäkään testiä ei ajettu ja testikattavuus on 0%, käytin pari tuntia siihen, että koitin googlailla mistä se voisi johtua, mutta en
löytänyt ratkaisua :(

#### Opittua
Tällä viikolla tuli kerrattua paljon geometriaa, kun netistä löydetty algoritmi ei suoraan toiminutkaan ja debuggaus osoitti, että ongelma
oli matematiikkapuolella, joten ongelman korjaamiseski piti vähän kerrata

#### Tällä viikolla käytetyt lähteet
- https://github.com/Yonaba/delaunay/blob/master/delaunay.lua
- https://www.geeksforgeeks.org/program-find-circumcenter-triangle-2/
- https://en.wikipedia.org/wiki/Bowyer%E2%80%93Watson_algorithm
- https://github.com/Bl4ckb0ne/delaunay-triangulation

#### Aikaa tällä viikolla käytetty noin 18 tuntia
- 14h Ohjelmointiin
  - Algoritmin kirjoittamiseen noin 2h
  - Algoritmin debuggaamiseen konsolilla noin 1h
  - Graph.java rakentamiseen noin 15min
  - Algoritmin debuggaamiseen graph.javalla ja algoritmin matematiikkapuolen uudelleenrakentaminen noin 11h
- 1h Javadoc kommenttien kirjoittamiseen
- 3h JUnit -testeihin
 - 1h Varsinaisten testien kirjoittamiseen
 - 2h Jacoco ongelman pähkäilyyn, johon lopulta ei löytynyt ratkaisua
 
### Ensi Viikolla
- Pienin virittävä puu Delaunay -algoritmin generoimasta verkosta ja käytävien generointi puun pohjalta
- ArrayListin toteuttaminen Delaunay:lle
