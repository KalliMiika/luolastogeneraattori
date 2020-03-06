## Käyttöohje

[Lataa ohjelma](https://github.com/KalliMiika/luolastogeneraattori/releases/tag/Release)

#### Ohjelman saa käyntiin komennolla kansion juuresta
```
java -jar luolastogeneraattori.jar
```

Oikeasta yläkulmasta valitaan käytettävä algoritmi, jonka jälkeen oikeaan
laitaan avautuu parametripalkki josta voi säätää ohjelman käyttämiä parametrejä
ja oikeasta alakulmasta löytyy algoritmin käyttämä aika ja muisti suorituksen jälkeen
käytetyn muistin laskeminen ei tosin toimi oikein :(

### VAROITUS
#### Liian ahneilla parametreillä tapahtuu hirveyksiä :(

### RandomWalk
|Nimi|Tyyppi|Kuvaus|
|---|---|---|
|Tunnels|int|Kaivettavien tunneleiden lukumäärä|
|maxLength|int|Kaivettavien tunneleiden maksimipituus|
|minLength|int|Kaivettavien tunneleiden minimipituus|

### Cellular Automata
|Nimi|Tyyppi|Kuvaus|
|---|---|---|
|P(Wall)|int|Todennäköisyys, jolla alustettava ruutu on seinä|
|Iterations|int|Iteraatioiden lukumäärä|
|Range 1|int|Etäisyys jonka säteeltä etsitään sieniä|
|Cutoff 1|int|Raja jolla ensimmäinen leikkaus suoritetaan|
|Range 2|int|Etäisyys jonka säteeltä etsitään seiniä|
|Cutoff 2|int|Raja jolla toinen leikkaus suoritetaan|

### TBD
|Nimi|Tyyppi|Kuvaus|
|---|---|---|
|Rooms|int|Generoitavien huoneiden lukumäärä|
|Collision|String|Törmäystarkistukseen käytettävä metodi|
|Spanning Tree|String|Virittävän Puun muodostukseen käytettävä metodi|
|Tree Cutoff|int|Todennäköisyys, jolla puuhun kuulumaton käytävä otetaan mukaan|

### Tiny Keep-ish
|Nimi|Tyyppi|Kuvaus|
|---|---|---|
|Rooms|int|Generoitavien huoneiden lukumäärä|
|Generation|String|Huoneiden generointi patterni|
|Collision|String|Törmäystarkistukseen käytettävä metodi|
|Large Cutoff|int|Minimi Pinta-ala, jolla huone luokitellaan isoksi|
|Spanning Tree|String|Virittävän Puun muodostukseen käytettävä metodi|
|Tree Cutoff|int|Todennäköisyys, jolla puuhun kuulumaton käytävä otetaan mukaan|

#### Tiny Keep-ish:llä on lisäksy demo ominaisuus, jolla voi askel askeleelta seurata algoritmin toimintaa huoneiden generoinnista maaliin
1. Valitse Tiny Keep-ish
2. Valitse Parametrit
3. Paina "Demo"
4. Paina "Spam This", kunnes se katoaa
5. Paina "Next", kunnes se katoaa
