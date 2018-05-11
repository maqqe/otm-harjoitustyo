# Testausdokumentti

Ohjelmaa on testattu yksikkö automatisoiduin JUnit testein sekä manuaalisesti järjestelmätasolla.

### Yksikkö- ja integraatiotestaus

##### Logiikka

Testattu JUnit testein luomalla instansseja vuorovaikuttavista olioista toistensa testeihin ja kokeilemalla yhteentoimivuutta.

##### DAO

Testattu hyödyntäen olemassa olevia konfiguraatiotiedostoja ja niiden sisältämää dataa.

##### Kattavuus

Käyttöliittymän ulkopuolisen koodin rivikattavuus yli 95 % ja haaraumakattavuus 90 %

##### Parannusideoita

Etenkin DAO-luokkaa varten olisi ollut syytä rakentaa testauskonfiguraatiotiedosto, joka olisi mahdollistanut itse pelin tiedostoista riippumattoman testauksen. Nyt muutokset pelin konfiguraatiotiedostossa määriteltyihin tiedostoihin voivat vaikuttaa testeihin.

### Järjestelmätestaus

Järjestelmätestaus on suoritettu manuaalisesti OSX ympäristössä siten, että config.properties-tiedostossa määritellyt tiedostot ovat sisältäneet kuranttia dataa.

### Sovellukseen jääneet laatuongelmat

Sovellus ei järkevästi ilmoita, mikäli konfiguraatiotiedostossa tai sen määrittelemissä tiedostoissa on jotain vikaa.  
