# Projekt: Ewoluujące potworki
Symulacja typu Agent Based Model, inspirowana modelem Predator-Prey. Głównym celem jest zasymulowanie zachowań różnego rodzaju potworków, które mogą ewoluować.

## Mapa, czyli miejsce akcji
Mapa składałaby się z sześciokatnych pól. Byłoby ich kilka rodzajów, wstępnie:
- pole podstawowe - łąki
- pola specjalistyczne - woda, góry, lasy

Na początku będzie ustalana wielkość mapy, mapa będzie generowana losowo. Każdy potworek będzie mógł poruszać się na polu podstawowym, a poszczególne rodzaje potworków na polach specjalistycznych.

## "Jagody", czyli jedzenie
Losowo generowane na mapie, są podstawowym pożywieniem potworków. Będzie ich kilka rodzajów. Każdy potworek (zwłaszcza basic) może "ewoluować" jeśli któryś rodzaj pokarmu który spożywa przeważa (np. ponad połowa to niebieskie jagody -> pływające). Wówczas zmienia się na inny typ potworka.

## Potworki, czyli Agenci
Potworki to główny obiekt badań symulacji. Każdy z rodzajów będzie się różnił wstępnymi statystykami.
| Statystyka    | Opis                                            |
| ---           | ---                                             |
| HP            | Punkty zdrowia określające żywotność potworka   |
| Atak          | Siła ataku potworka w ramach interakcji         |
| Zasięg        | Obszar, od którego potwór może ścigać innego potwora |
| Szybkość      | Prędkość poruszania się potworka na mapie |
| *XP | Poziom doświadczenia, prawdopodobnie rozbity na 3 osobne paski różnych rodzajów |

*_Możliwość trybu gdzie XP jest jedyną statystyką_

HP, Atak, Zasięg i Szybkość to statystyki ustalone na sztywno, nie są zmieniane w czasie życia potworka. **XP to najważniejsza statystyka.** Wszystkie potworki zaczynają od 0. Aby je zwiększyć, potworki mogą jeść jagody lub pokonywać inne potworki.

Każdy z potworków może:
- poruszać się po mapie - z ustaloną prędkością, w dowolnym kierunku (dzięki zastosowanio wektorów)
- jeść jagody - aby zwiększyć swoje XP
- atakować - czyli poruszać się w stronę słabszego potworka, aby zredukować konkurencję oraz zwiększyć swoje XP
- rozmnażać się - podział na dwa nowe potworki tego samego rodzaju (o jednej losowo zwiększonej statystyce) po przekroczeniu określonego poziomu XP. 
- ewoluować - po zaistnieniu jakiegoś warunku, wstępnie zwiększenie XP innego rodzaju.

Byłyby cztery rodzaje potworków:
| Rodzaj  |Dozwolone pola | Opis |
| ---     | ---           | ---|
| Basic   | łąki          | domyślnie słabe ale szybko się rozmnażają |
| Wodne   | łąki, woda    | silniejsze od basic |
| Latające| łąki, góry    | silniejsze od basic |
| Drzewne | łąki, lasy    | silniejsze od basic |

Gdy potworki różnych rodzajów się spotykają to walczą ze sobą, walka przebiega turowo. Przegrany znika, wygrany dostaje jakiś procent jego XP.


