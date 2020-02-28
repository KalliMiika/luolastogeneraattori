# Viikon 6 Raportti

### Näillä kahdella Viikolla

#### Ohjelmointia

- Kaksi eri versiota virittäviä puita
  - Ensimmäinen versio (basic) alottaa ensimmäisestä luodusta huoneesta, ja lisää kaaria
  puuhun jotka johtavat huoneeseen, joka ei vielä ole osa puuta.
  - Toinen versio valitsee huoneen satunnaisesti, kerää kaikki siihen huoneeseen kytköksissä olevat käytävät,
  jotka johtavat huoneeseen, jossa ei olla vielä käyty ja valitsee niistä satunnaisen käytävän, 
  ja ottaa käytävän toisessa päässä olevan huoneen tarkasteltavaksi ja jatkaa tätä prosessia kunnes kaikissa huoneissa on käyty.
  Tässä kuitenki vaikuttaisi olevan toistaiseksi jokin bugi, koska tätä testaillessa välillä generoitui huone johon ei johtanut
  yhtäkään käytävää.
  
- Omia tietorakenteita
  - Corridor -Olio, joka kuvastaa kahden huoneen välillä kulkevaa käytävää
  - Step, Käytävien generoinnissa linkitetyn listan solu
  - StepPriorityQueue, PriorityQueue joka sisältää Step -Olioita
  - CorridorList, ArrayList joka sisältää Corridor -Olioita
  - TriangleList, ArrayList joka sisältää Triangle -Olioita
  - RoomStack, Pino joka sisältää Room -Olioita
  
- Luolastongenerointialgoritmia viety eteenpäin siten, että generoitujen huoneiden välille Delaunay Triangulation -algoritmilla
rakennetusta verkosta muodostetaan virittävä puu johon lisätään muutama ylimääräinen käytävä ja generoidut käytävät piirretään kartalle.
-Käytävien generoimiseen tehty 3 erilaista algoritmia, joista yksi on jätetty vähän puolitiehen, koska se ei vaikuttanut kovin mielenkiintoiselta
tavalta generoida käytävää. 
  - Puolitiehen jätetty algoritmi
    - Edetään x akselilla, kunnes käytävän kaivurin x koordinaatti täsmää määränpäähuoneen x koordinaatin kanssa ja 
    sitten edetään y akselilla kunnes saavutaan määränpäähuoneeseen.
    
  - Rajoittamaton A*
    - Selvitetään ensin sopivat kohdat oville käytävän molemmissa päissä olevissa huoneissa, jonka jälkeen suoritetaan A* haku
    käytävän alkupään (from) huoneen ovesta käytävän loppupään (to) huoneen oveen.
    
  - Rajoitettu A* (ei saa liikkua kulmittain)
    - Muuten sama kuin ylempi, mutta rajoitettu että liikkuminen saa tapahtua vain pysty tai vaakasuunnassa

#### Dokumentaatio
- JavaDoc kommentoinnit pidetty ajantasalla
- Testausdokumentti pidetty ajantasalla
- Toteutusdokumenttiin ei mitään lisättävää vielä, perjaatteessa kolmas generointi algoritmi on "valmis", mutta en ole vielä päättänyt, että koitanko
laajentaa sitä tai tehdä siitä monipuolisemman, esim niin että käytetään pääosin samaa algoritmia, mutta käytäviengenerointi toteutetaan
eri tavoin vai teenkö läheissä mainitun tinyKeep pelissä käytetyn algoritmin, joka on hyvin samankaltainen kuin juuri toteutettu algoritmi,
mutta käytävien generointi on toteutettu täysin eri tavalla.

#### Testaus
- JUnit testit pidetty ajantasalla, generaatioalgoritmejä lukuunottamatta, lisäsin ne toistaiseksi jacocon excludeen

#### Checkstyle
- Koodi pidetty suurinpiirtein määritellyn Checkstylen hyväksyttävänä

#### Aikaa Viimeisillä kahdella viikolla käytetty noin 34 tuntia
- 32h Ohjelmointiin ja debuggailuun
- 1h Javadoc kommenttien kirjoittamiseen
- 1h JUnit -testeihin
 
### Ensi Viikolla
- Todennäköisesti koitan erilaisia algoritmeja käytävien generointiin tällä viikolla valmiiksi saadulle luolastogeneraattorille. 
Vaihtoehtoisia ideoita olisi ainakin lähteissä mainitun tinyKeep pelin idea, missä huoneita luodaan about niin paljon kun kentälle mahtuu,
ja kaikki huoneet joita käytävät leikkaavat "aktivoidaan" ja käytävät oikeastaan koostuvat näistä aktivoiduista huoneista. Toinen idea olisi 
tunkea kartta huoneiden generoinnin jälkeen cellularAutomatan läpi kunnes kaikki virittävän puun määrittämät reitit huoneiden väliltä löytyvät
tällöin voisi ehkä saada aikaan "luonnollisen" näköiset käytävät huoneiden välille

#### [mainittu lähde](https://www.gamasutra.com/blogs/AAdonaac/20150903/252889/Procedural_Dungeon_Generation_Algorithm.php)
