# Bar Fight Simulator

A game about drinking beer and crushing your enemies while you're at it.


## Dokumentaatio

[Käyttöohje](https://github.com/maqqe/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/maqqe/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/maqqe/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/maqqe/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/maqqe/otm-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)


## Releaset

[Viikko 5](https://github.com/maqqe/otm-harjoitustyo/releases/tag/Viikko5)

[Viikko 6](https://github.com/maqqe/otm-harjoitustyo/releases/tag/Viikko6)

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

### JavaDoc

JavaDoc generoidaan komennolla

> mvn javadoc:javadoc

JavaDocia pääsee tarkastelemaan avaamalla tiedoston target/site/apidocs/index.html
