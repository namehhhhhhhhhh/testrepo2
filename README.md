# poc-rtb

## Requisiti
```
git
docker (linux kernel)
docker-compose
```

## Il sistema
Il sistema è composto da 4 container:
 - **frontend_dev**: Container basato su immagine custom di `node:19` e precompiata con le dependencies, che gestisce il frontend esponendo la porta `5000`.
 - **backend_dev**: Container basato su `eclipse-temurin:17-jdk-alpine` che gestisce tramite `mvnw` il ciclo di vita (compilazione differenziale ed esecuzione) del backend. Espone la porta `5002` per testare direttament ele chiamate.
 - **database**: Container basato su `postgres:14.6` che gestisce il database. Non espone porte perchè è gestibile dal Adminer.
 - **adminer**: Container basato su `adminer` che gestisce con interfaccia minimale il database. Espone la porta `5001` per la visualizzazione dell'interfaccia. Le coordinate di login sono `postgres:postgres@postgres/testdb`

## Esecuzione

Il comando per l'esecuzione è
```
$ docker-compose up
```
e la combinazione `Ctrl+D` termina l'esecuzione dei container mettendoli in stato di STOP.

E' possibile l'esecuzione di particolari container appendendo al comando i nomi dei container da avviare.

L'interfaccia web è disponibile all'indirizzo `http://localhost:5000/`.

## Dati di login

I dati di login per il test sono:
 - **username**: user
 - **password**: pass
