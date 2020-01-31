# Viikon 3 Raportti

*Raportissa referoidaan useassa kohdassa tähän [lähteeseen](http://roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels)*

### Tällä Viikolla

#### Ohjelmointia
- Cellular Automata -algoritmin toteutus ja hienosäätö
  - Huomasin että pelkällä perus Cellular Automata -algoritmilla luolastoista tuli sen näköisiä kun olisi vain nyrkillä lyönyt
  reijän siihen, joten lähdin kokeilemaan lähteessä suositeltuja lisäyksiä algoritmiin ja niiden arvojen muokkailua kunnes algoritmi
  alkoi tuottamaan mielestäni hyvän näköisiä karttoja
  - Algoritmista myös versio joka ei pyydä Cave -oliolta randomgeneroitua karttaa, vaan ottaa yhtenä syöteparametrinä seedinä käytettävän kartan,
  jonka voi generoida esimerkiksi RandomWalk -algoritmilla.
- Point -Oliota päivitetty
  - lisätty pisteiden vähennys (vähennetään pisteen B koordinaatit pisteen A koordinaateista)
  - tuunattu sijaintien tarkistusta siten, että reunalle ei saa mennä (0 tai max koordinaatteihin siis)
- Cave -Oliota päivitetty
  - Oliolle lisätty staattiset metodit joilla voidaan suoraan generoida uusi Cave -Olio siten, että se käyttää jotakin tähän mennessä
  rakennettua luolastongenerointialgoritmia
  - Oliolle lisätty staattinen metodi, joka randomgeneroi kartan siten, että annetun kartan reunoilla on seiniä ja sisällä ruutu on seinä annetulle todennäköisyydellä
  
#### Dokumentaatio
- JavaDoc kommentoinnit pidetty ajantasalla

#### Testaus
- JUnit testit pidetty ajantasalla, tosin tässä pieniä ongelmia

#### Checkstyle
- Koodi pidetty suurinpiirtein määritellyn Checkstylen hyväksyttävänä, joitakin metodeita lukuunottamatta (En halua rikkoa metodia mielestäni turhaan, pelkästään sen takia että metodi on yhden rivin liian pitkä)

#### Opittua
Cellular Automatalla tuli leikittyä aika paljon tällä viikolla ja tuntuu että sai paremman käsityksen siitä miten käyttäytyy kun tiettyjä parametrejä muokkaa tästä lähtenyt mietintään ajatus
että voisiko algoritmia muokata hieman siten, että sitä voisi käyttää huoneiden välisten käytävien generointiin mielenkiintoisemman näkösten käytävien saavuttamiseksi.

#### Epäselvää
Luolastogenerointialgoritmien JUnit testaus.
- Miten luoda JUnit -testi luolastongenerointialgoritmille niin, että siinä on mitään järkeä?

#### Aikaa tällä viikolla käytetty noin 16 tuntia
- 12h Ohjelmointiin
  - Itse algoritmin toteutukseen noin 2h
  - Algoritmin päivitys lähteessä mainittuun paranneltuun versioon noin 1h
  - Cave ja Point -olioiden muokkauksia ja päivityksiä uuden algoritmin hyväksi noin 1h
  - Erilaisilla parametreilla ja erilaisten lisäominaisuuksien testailua noin 8h
    - Testasin lähteessä mainittua ideaa, että vedetään poikittain tai pitkittäin käytävä keskelle randomgeneroitua
    pohjaa joka varmistaa, että generoitu luolasto on yhtenäinen.
    - Sekä lähteessä mainittua floodfill tekniikkaa suljettuiden sivuluolien täyttämiseksi, mutta toistaiseksi
    mielestäni ei tarvitse miettiä liikaa sitä, että onko sielä niitä suljettuja sivualueita vai ei.
    - Poistin molemmat viritelmät koska molemmat aiheuttivat mielestäni enemmän päänvaivaa kuin mitä ne korjasivat,
    Tyhjä käytävä vaikutti liikaa lopputulokseen ja floodfill meni joskus ohi "pääluolastosta" kun arvalla valittiin vain ensimmäinen
    solu joka oli lattiaa ja siitä leveyshaulla täytettiin se. Floodfill varmaan olisi näistä lupaavampi, mutta siihen pitäisi keksiä
    jokin simppeli päättelyalgoritmi että mikä luolastoista on "pääluolasto".
    Saatan palata näiden kanssa ihmettelemiseen myöhemmin
- 30min Kommentteihin
- 30min JUnit testeihin
- 3h Uusien lähteiden metsästykseen, mutta ei ole ollut onnea näiden kanssa toistaiseksi

### Ensi Viikolla
- Aloitan rakentamaan määrittelydokumentissa neljänneksi esiteltyä algoritmia:
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
- Jos aikaa riittää, niin jatkan lähteiden etsimistä, olisi kiva saada vielä ainakin yksi "simppeli" algoritmi mukaan projektiin.
