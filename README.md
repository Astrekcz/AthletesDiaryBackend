# Athletes Diary

Aplikace slouží atletům jako nástroj pro evidenci tréninků, analýzu výkonů a plánování dalšího zlepšování. Zjednodušuje správu dat o trénincích a poskytuje užitečnou zpětnou vazbu.


Hlavní uživatelé jsou atleti všech úrovní, od amatérů po profesionály, včetně trenérů.
### Použité technologie a knihovny

#### Backend
- **Java 17**: Programovací jazyk, ve kterém je napsán backend.
- **Spring Boot 6.1**: Framework pro tvorbu REST API.
    - Moduly:
        - Spring Web: Pro implementaci webových endpointů.
        - Spring Security: Pro autentizaci a autorizaci uživatelů.
        - Spring Data JPA: Pro komunikaci s databází pomocí ORM (Hibernate).
- **Lombok**: Knihovna pro eliminaci boilerplate kódu (např. generování getterů, setterů, konstruktorů).
- **MySQL**: Databázový systém pro ukládání uživatelských dat a tréninků.
- **Maven/Gradle**: Nástroj pro správu závislostí a buildování projektu.

#### Frontend (Plán)
- **React**: Framework pro tvorbu uživatelského rozhraní.
- **Axios**: Knihovna pro komunikaci s backendem.
- **Chart.js**: Pro vykreslení grafů výkonnosti.

#### Nástroje a prostředí
- **IntelliJ IDEA Ultimate**: Vývojové prostředí.
- **Postman**: Nástroj pro testování API.
- **Git**: Pro správu verzí kódu (GitHub jako hosting repozitáře).

## Plán funkcionalit

## 1. Aktuálně hotové funkce

### Backend
1. **Správa uživatelů**:
    - Registrace uživatelů.
    - Přihlášení s využitím JWT tokenů (kompletní autentizace a autorizace).
   přes AuthController
    - Mazání uživatelů.
    - API na správu uživatelů dostupné přes UserManageController.

2. **Správa tréninků**:
    - Vytváření nových tréninků.
    - Ukládání tréninků do databáze.
    - Mazání tréninků.
    - API dostupné přes TrainingController.

3. **Bezpečnost**:
    - Kompletní implementace autentizace pomocí JWT tokenů.
    - Zabezpečené endpointy pro správu tréninků i uživatelů.

### Databáze
- Návrh tabulek pro uživatele a tréninky.
- Vytvořené relace mezi entitami.

---

## 2. Plánované funkce

### Backend
1. **Rozšíření API funkcionalit**:
    - **Úprava tréninků**:
        - Přidání endpointu pro aktualizaci existujících tréninků (např. změna času nebo vzdálenosti).
    - **Vyhledávání a filtrování tréninků**:
        - Endpoint pro získání tréninků podle data, typu nebo jiných parametrů.


### Frontend 
1. **Dashboard uživatele**:
    - Zobrazení seznamu tréninků.
    - Přehled statistik uživatele (např. počet tréninků, nejlepší čas, celková vzdálenost).
2. **Detail tréninku**:
    - Stránka s detailními informacemi o konkrétním tréninku.
3. **Uživatelské funkce**:
    - Možnost upravit svůj profil.
    - Změna hesla.
    - Registrace, odhlášení, přihlášení.

### Další nápady
- **Plánování tréninků**:
    - Možnost přidat budoucí tréninky do kalendáře.

## 3. Priorita funkcí
- Nejdůležitějsí je v momentální chvíli dodělat frontend s aktuální verzí backendu.


## 4. Dlouhodobé cíle

- Dokončení frontendu a plná integrace s backendem.
- Rozšíření aplikace o analytické funkce a vizualizaci dat.
- Optimalizace výkonu backendu (např. pro větší objem dat).
- Vytvoření veřejného dema aplikace.








