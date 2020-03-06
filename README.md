# Luolastogeneraattori

- [Käyttöohje](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/k%C3%A4ytt%C3%B6ohje.md)
- [Määrittelydokumentti](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/m%C3%A4%C3%A4rittelydokumentti.md)
- [Toteutusdokumentti](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/toteutusdokumentti.md)
- [Testausdokumentti valmiille ohjelmalle](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/testausdokumenttiValmis.md)
- [Testausdokumentti](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/testausdokumentti.md)
- Viikkoraportit
  - [Viikon 1 Raportti](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/viikko_raportti_1.md)
  - [Viikon 2 Raportti](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/viikko_raportti_2.md)
  - [Viikon 3 Raportti](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/viikko_raportti_3.md)
  - [Viikon 4 Raportti](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/viikko_raportti_4.md)
  - [Viikon 5 Raportti](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/viikko_raportti_5.md)
  - [Viikon 6 Raportti](https://github.com/KalliMiika/luolastogeneraattori/blob/master/dokumentaatio/viikko_raportti_6.md)

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
