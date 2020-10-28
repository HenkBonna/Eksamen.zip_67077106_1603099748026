# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020

# Krav til innlevering

Se oblig-tekst for alle krav, og husk spesielt på følgende:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* git bundle er levert inn
* Hovedklassen ligger i denne path'en i git: src/no/oslomet/cs/algdat/Eksamen/EksamenSBinTre.java
* Ingen debug-utskrifter
* Alle testene i test-programmet kjører og gir null feil (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet


# Beskrivelse av oppgaveløsning (4-8 linjer/setninger per oppgave)

Warnings: Fikk 4 Warnings av intelliJ: 

Warning:(10, 34) Non-ASCII characters in an identifier  
// Bruk av 'ø' i deklarasjon av 'høyre'. Skaper ingen problem. 

Warning:(236, 32) Non-ASCII characters in an identifier 
// Bruk av 'ø' i metodenavn 'førstePostorden'. Skaper ingen problem.

Warning:(275, 34) Dereference of 'current' may produce 'NullPointerException'   
// Predikerer et potensielt null-resultat i en while løkke som går til nestePostorden. Grunnet hvordan oppbygget, vil dette ikke skape problemer for oss.

Warning:(295, 36) Argument 'nestePostorden(p)' might be null    
// Predikerer et potensielt null-resultat i en while løkke som går til nestePostorden. Grunnet hvordan oppbygget, vil dette ikke skape problemer for oss.

Vi har brukt git til å dokumentere arbeidet vårt. Jeg har 16 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

* Oppgave 1: Løste ved å kopiere den gitte kildekoden fra kompendiet; Programkode 5.2.3 a). Måtte bare sørge for at noden 
også fikk en foreldre peker.
* Oppgave 2: Ble løst ganske direkte. Først måtte koden traversere nedover treet til den fant hvor verdien burde ligge. 
Dersom verdien var å finne der, øker jeg telleren.
* Oppgave 3: Brukte en del tid på å få disse til å fungere, og måtte skissere en del på arket for å prøve å forstå hva 
jeg gjorde. førstePostorden var temmelig ok, da den bare kunne kopieres fra Programkode 5.1.7 h).
nestePostorden var biten som tok mest arbeid. Jeg så på beskrivelsen av Postorden fra kompendiet, funnet et par avsnitt 
under Programkode 5.1.7 h), og prøvde å oversette denne, slik jeg forsto det.
* Oppgave 4: Denne tok en del tid å debugge. Måtte finne en løsning for å avslutte rekursjonen, da den også må fungere 
for rot-noden. Å sjekke om nåværende node er rot, er dermed ikke et punkt man kan ende på. Gjorde derfor rekursjon bare 
frem til man befant seg på nest siste node. 
* Oppgave 5: Løsningen til Serialize var basert på notater fra en forelesning jeg hadde tidligere, men med noen mindre 
endringer for å få det til å fungere. Jeg snubla litt langs veien ved å bruke 'removeLast' da jeg egentlig burde ha 
brukt 'removeFirst', men dette klarte jeg løse vha. System.out.print()-debugging, og IntelliJ's verktøy.
* Oppgave 6: Måtte bruke IntelliJ's debugging-verktøy mye for å løse dette, samt skissere opp logikken for binær-
treet på papir. Fikk alt til slutt til å passere testene, men jeg må si meg usikker på hvordan nøyaktig fjernAlle 
egentlig får til å bli riktig. Jeg skjønner ikke helt selv hva jeg har gjort – har kommentert dette.
