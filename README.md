# Pokemon Battle

Dette er et Pokemon 1-mot-1 spill, inspirert av Lab2 i INF101, V25.

Hovedfokuset med dette spillet var å lære Objektorientert-programmering og konsepter innenfor det enda bedre.

# Hvordan funker det?

Det er to spillere, en User (deg) og en veldig simpel 'AI' som du fighter mot. Dere får tildelt hver deres Pokemon med forhåndsbestemte attributter (HP, liste med forskjellige attacks, type, m.m)

Når spillet starter, får du et valg mellom fire angrep du kan utføre. Hvert angrep har en forskjellig type (Water, Rock, Bug osv...). Forskjellige typer er ekstra effektive mot hverandre. For eksempel så er Water dobbelt så effektiv mot Fire enn mot enkelte andre typer. Dette betyr at angrepets damage dobles.
Dette går også andre veien. Om du angriper med et Fire angrep mot en Water-Pokemon, så gjør angrepet halvparten så mye skade (0.5x).

Etter du har angrepet, så er det AIs tur å angripe. Måten AI angriper på er å velge et tilfeldig tall fra 1-4, som deretter indekserer gjennom en liste med tilgjengelig angrep.
Når AI har valgt et tall (og angrep), så utfører han angrepet, og du mister tilsvarende HP.

Som i de fleste andre 1v1 spill, så er om å gjøre å ikke få 0 HP. Om man får 0 HP, taper man.

# Beskrivelse

Spillet simulerer en 1-mot-1 kamp mellom en menneskelig "trainer" og en simpel AI-motstander. Spilleren kontrollerer en Pokemon (for øyeblikket Rayquaza, men kan simpelt endres om ønsket).
AI kontrollerer en annen Pokemon, (Giratina for øyeblikket. Kan også enkelt endres).

Kampen vises i et GUI med en bakgrunn og to GIFs av Rayquaza og Giratina. I tillegg er det HP-barer, navn på Pokemons og en Tekst-boks med hendelseslogg. Etter hvert som kampen går videre, oppdateres HP barene, og når en av HP-barene går til 0, så får vi en vinner.

# Kjøring

Når man kjører spillet, er det viktig at man er koblet til internett for at GUIen (bakgrunn og sprites) skal laste inn ordentlig, ettersom disse er hentet fra URL og ikke lagret lokalt i prosjektet.

# Bruk av ressurser fra internett

Jeg har brukt to GIFs og et bakgrunnsbilde fra internett. Jeg eier ingen av disse og bruker de kun i dette prosjektet, som kun er til for læring. Jeg skal ikke bruke dette prosjektet til noe kommersielt. Det står litt mer om dette i PokemonGUI.java. Håper det er ok

# Video

Link til Youtube video om prosjektet:

https://youtu.be/nOXgQaRhUc8
