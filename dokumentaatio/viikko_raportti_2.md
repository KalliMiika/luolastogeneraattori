# Viikon 2 Raportti

### Ennen tämän viikon panostuksia päätin uudelleenmiettiä projektini ekan viikon palautteen perusteella
- [Päivitetty määrittelydokumentti](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/m%C3%A4%C3%A4rittelydokumentti.md)

### Tällä Viikolla

#### Ohjelmointia
- Cave -Olio tehty aluilleen, toistaiseksi ei kovin mielenkiintoinen
- Point -Olio, jota voi käyttää pisteenä tai vektorina.
- RandomWalk Algoritmi tehty valmiiksi

#### Dokumentaatio
- JavaDoc kommentoinnit lisätty tähän mennessä kirjoitetulle koodille

#### Testaus
- JUnit testit kirjoitettu tähän mennessä kirjoitetulle koodille

#### Checkstyle
- Checkstyle otettu käyttöön

#### Lähteitä
- Viimeviikon palautteen inspiroimana lähdin kaivelemaan lisää lähteitä ja löysin muutamia mielenkiintoisia
luolastongenerointi algoritmeja, jonka johdosta päätin vaihtaa projektin tavoitteen yhden algoritmin rakentamisesta, monen
erilaisen algoritmin rakentamiseen ja niiden vertailuun.

#### Opittua
- Lähteitä kahlatessa tuli nähtyä monia erilaisia lähestymistapoja luolastogeneraattorien rakentelussa, joista jokainen generoi todella erinäköisiä luolastoja.

#### Epäselvää
- Miten vertailla luolastogeneraattoreita?
  - Onko luolastogeneraattorin aikavaativuus oikeasti relevanttia, olettaen, että se toimii _kyllin_ nopeasti, että se ehtii esimerkiksi tason vaihtuessa luoda seuraavan tason niin nopeasti, että pelaajan näkökulmasta se näyttää siltä että taso oli ennaltaluotu.
  - Sen sijaan laittaisin painoa sille, että minkä näköisiä luolastoja algoritmi generoi, mutta tämä taas on kohtalaisen subjektiivinen ominaisuus.
  - Tähän voisi kaivata ideoita
  
#### Aikaa tällä viikolla käytetty noin 8 tuntia
- 3h Ohjelmointiin
- 30min Kommentteihin
- 30min JUnit testeihin
- 4h Lähteiden etsimiseen ja huolella lukemiseen

### Ensi Viikolla
- Cellular Automata -Algoritmin rakentelu taivoite saada se valmiiksi kokonaan
- Mielenkiinnosta haluaisin tuikata RandomWalk -algoritmilla tehdyn kartan parametriksi Cellular Automata -algoritmille ja katsoa että millaisia luolastoja se sylkee ulos
- Koitan löytää vielä muita mahdollisia generaatioalgoritmeja
