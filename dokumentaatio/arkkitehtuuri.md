# Arkkitehtuurikuvaus

## Rakenne

Pelin käyttöliittymä on pakkauksessa barfightsimulator olevassa tiedostossa MainApp.java. MainApp java tekee instanssit ui ja dao pakkauksissa olevista MissionLoader.java ja LocalizableObjectDao luokista.
MissionLoader luokka hyödyntää DAO luokkaa pelin hahmojen ja esineiden koordinaattien hakemiseen.
Varsinainen pelin pyörittäminen tapahtuu MissionLoader luokan alla. MainApp hakee MissionLoader luokalta
pelitilanteen joka vuoro ja piirtää hahmot saatujen koordinaattien perusteella.

(Luokat jäivät epäloogisiin pakkauksiin pelin kasvaessa. Myöhemmät yritykset siirtää luokat parempiin pakkauksiin johtivat virheisiin ohjelman suorituksessa eikä niitä siten saatu siirrettyä järkevämpiin pakkauksiin.)

## Luokkakaavio

Alla LocalizableObjectin perivien luokkien hierarkia:

![Luokkakaavio](https://github.com/maqqe/otm-harjoitustyo/blob/master/dokumentaatio/pics/luokkakaavio.png)

## Päätoiminnallisuudet

Pelaajan hyökätessä vihollisen kimppuun on toimintalogiikka sekvenssikaaviona esitettynä seuraava

![attackSequenceDiagram](https://github.com/maqqe/otm-harjoitustyo/blob/master/dokumentaatio/pics/attackSequenceDiagram.png)

Kun pelaaja antaa järkevän komennon graafisessa käyttöliittymässä, ohjataan komento MissionLoaderin metodille playTurn(String command), joka vastaa käytännön toteutuksesta.
Metodi siirtää ensin pelaajaa komennon mukaisesti, tai käyttää esineen. Mikäli pelaajaa siirretään, tarkastetaan, onko kohderuudussa vihollinen. Jos, sen kimppuun hyökätään ja pelaaja pysyy paikallaan.
Mikäli ruutu on tyhjä, liikutetaan pelaajaa. Epäjärkevät komennot johtavat tässä vaiheessa metodista poispaluuseen.
Tämän jälkeen peli tarkistaa, onko vihollisia hengissä. Mikäli ei, siirrytään seuraavaan tehtävään tai mikäli tehtävä oli viimeinen, lopetetaan peli. Mikäli vihollisia on jäljellä, jokaiselle kutsutaan ensin attack() metodia, jonka jälkeen ne jahtaavat pelaajaa mikäli eivät jo ole viereisessä ruudussa tai estyneitä. Tämän jälkeen tarkistetaan, onko pelaaja vielä hengissä (hit points >= 0). Mikäli ei, peli päättyy. Mikäli kyllä, poistutaan metodista odottamaan mahdollista uutta komentoa.

