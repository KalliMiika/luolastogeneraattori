# Luolastogeneraattori

- [Määrittelydokumentti](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/m%C3%A4%C3%A4rittelydokumentti.md)
- Viikkoraportit
  - [Viikon 1 Raportti](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/viikko_raportti_1.md)

## Komentorivikomennot

### Testaus

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/KalliMiika/luolastogeneraattori/blob/master/luolastogeneraattori/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
