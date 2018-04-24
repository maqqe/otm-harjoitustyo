# Bar Fight Simulator

A game about drinking beer and crushing your enemies while you're at it.


## Dokumentaatio

[Vaatimusmäärittely](https://github.com/maqqe/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/maqqe/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/maqqe/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot

### Testaus

Testit ajetaan komennolla

>  mvn test

Testikattavuusraportin generointi tapahtuu komennolla

> mvn test jacoco:report

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

### .jar:n generointi

Suoritettava .jar generoidaan komennolla

> mvn package

Komento generoi hakemistoon target suoritettavan jar-tiedoston BarFightSimulator-1.0-SNAPSHOT.jar

### Checkstyle

Tiedostossa [checkstyle.xml](https://github.com/maqqe/otm-harjoitustyo/blob/master/BarFightSimulator/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla

> mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html
