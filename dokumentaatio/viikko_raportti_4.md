# Viikon 4 Raportti

### Tällä Viikolla

#### Ohjelmointia
- Uuden luolastogenerointialgoritmin rakentelu laitettu aluilleen
  ```
  1. Luodaan randomgeneroituja huoneita n kappaletta
  2. Ajetaan simulaatiota, jossa huoneet törmäilevät toisistaan ja seinistä, 
     kunnes mikään huoneista ei mene päällekkäin toisten huoneiden ja seinien kanssa.
  ```
- Luotu Room -Olio joka kuvastaa randomgeneroitua huonetta
  - sijainti (Point), Leveys (int), Korkeus (int) ja säde (int) ja id (lähinnä käytetty debuggaamiseen) 
  - metodi checkCollision(Room room), joka tarkastaa leikkaavatko kahden Room-olion ympärille viritetyt ympyrät
  - metodi checkCollisionWithWalls(), joka tarkastaa leikkaako Room-olion ympärillä oleva ympyrä seiniä
  - metodi solveCollision(Room room), joka laskee mihin suuntiin törmäävien huoneiden kuuluisi kimmota törmätessään.
  - metodi drawWalls, joka piirtää huoneen Cave -Olion char[][] karttaan.
  - metodi drawCircle, joka piirtää huoneen "kuplan" Cave -Olion char[][] karttaan.
  - metodi crawCenter, joka piirtää huoneen id:tä kuvastavan kirjaimen Cave -Olion char[][] karttaan
  
#### Dokumentaatio
- JavaDoc kommentoinnit pidetty ajantasalla

#### Testaus
- JUnit testit pidetty ajantasalla, generaatioalgoritmejä lukuunottamatta

#### Checkstyle
- Koodi pidetty suurinpiirtein määritellyn Checkstylen hyväksyttävänä

#### Epäselvää
- 4. Viikon deadlinessä on mainittu, että pitäisi aloittaa Aloitettu suorituskyky- tai muu aiheeseen sopiva testaus, en kuitenkaan ole vielä keksinyt, että miten toteuttaisin tämän ohjelmallisesti niin, että siinä testataan jotain luolastogeneroinnin kannalta oleellista.
Tällä hetkellä työn alla olevalle algoritmille voisi kenties testata että mistä tahansa huoneesta pääsee mihin tahansa huoneeseen ja, että kaikkien huoneiden ympärille on onnistuttu generoimaan seinät niin ettei niissä ole ylimääräisiä reikiä, mutta algoritmi on vielä vaiheessa, joten testejä ei voi toteuttaa. Valmiille algoritmeille en keksinyt mitään testattavaa.

#### Aikaa tällä viikolla käytetty noin 14 tuntia
- 12h Ohjelmointiin
- 30min - 1h Kommentteihin
- 1h JUnit testeihin

### Ensi Viikolla
- Tarkoitus olisi aloittaa toteuttamalla Delaunay triangulation algoritmi huoneista koostuvan verkon rakentamiseksi, 
josta voidaan muodostaa pienin virittävä puu jotta saadan pienin mahdollinen yhdistelmä käytäviä niin, että mistä tahansa
huoneesta on reitti mihin tahansa huoneeseen.
- ^ toteutuksen jälkeen loppuviikon ajattelin käyttää käytävien generointi algoritmin suunnitteluun, alustavasti varmaan teen
jotakin perus L muotoisia käytäviä, mutta kiinnostaisi generoida "mielenkiintoisia" käytäviä huoneiden välille.
- Jos aikaa riittää, niin jatkan lähteiden etsimistä, olisi kiva saada vielä ainakin yksi "simppeli" algoritmi mukaan projektiin.
