# Report

## Indice

- [Introduzione](#1-introduzione)
- [Modello di Dominio](#2-modello-di-dominio)
- [Requisiti Specifici](#3-requisiti-specifici)
- [Manuale Utente](#7-manuale-utente)
- [Analisi Retrospettiva](#9-analisi-retrospettiva)


## 1 Introduzione
<p>

**Battleship** è un'applicazione che permette di giocare a battaglia navale, con interfaccia a linea di comando (CLI). Per lo sviluppo delle implementazioni descritte in seguito si è formato un team denominato "Thompson" composto da cinque ragazzi del corso B del secondo anno di "informatica" dell'Università degli studi di Bari Aldo Moro durante l'A.A. 2022/23.

I componenti del gruppo sono:
<ul>
<li>Lorenzo Matera</li>
<li>Nicola Mastromarino</li>
<li>Leo Mastronardi</li>
<li>Emanuele Russo</li>
<li>Roberto Rotunno</li>
</ul>
</p>

[Torna all'indice](#indice)

## 2 Modello di dominio
![modelloDiDomino](./img/modelloDiDominio.png)

[Torna all'indice](#indice)

## 3 Requisiti specifici

### 3.1 Requisiti funzionali

<ul>
<strong>
<li>Come giocatore voglio mostrare l'help con elenco comandi</li>
<li>Come giocatore voglio chiudere il gioco</li>
<li>Come giocatore voglio impostare il livello di gioco per variare il numero massimo di tentativi sbagliati</li>
<li>Come giocatore voglio mostrare il livello di gioco e il numero di massimo di tentativi falliti</li>
<li>Come giocatore voglio mostrare i tipi di nave e i numeri</li>
<li>Come giocatore voglio iniziare una nuova partita</li>
<li>Come giocatore voglio svelare la griglia con le navi posizionate</li>
</strong>
</ul>

### 3.2 Requisiti non funzionali
<ul>
<li> <p><strong>Il container docker dell'app deve essere eseguito da terminali che supportano Unicode con encoding UTF-8 o UTF-16</strong><br>
Elenco di terminali supportati<br>
Linux:<br>
- terminal<br>
Windows<br>
- Powershell<br>
- Git Bash (in questo caso il comando Docker ha come prefisso winpty; es: winpty docker -it ...)<br>

**Comando per l’esecuzione del container**<br>
Dopo aver eseguito il comando docker pull copiandolo da GitHub Packages, Il comando Docker da usare per eseguire il container contenente l’applicazione è:<br>

`docker run --rm -it ghcr.io/softeng2223-inf-uniba/battleship-thompson:latest`

</p></li>
</ul>

[Torna all'indice](#indice)

## 7 Manuale utente
Il gioco si svolge su una griglia, dove il sistema posiziona in modo casuale le navi all'inizio di una partita. L'obiettivo del giocatore è quello di indovinare la posizione delle navi nemiche e cercare di affondarle attaccando le caselle della griglia.<br>
Il gioco termina quando tutte le navi nemiche sono state affondate o quando si esauriscono le mosse disponibili. Il numero di mosse disponibili dipende dalla modalità di gioco scelta, che può essere facile, media o difficile.<br>Il giocatore vince se 
affonda tutte le navi prima di esaurire le mosse.
<br>

Per interagire con il gioco, viene utilizzata l'interfaccia a riga di comando (CLI). Di seguito si riporta l'elenco dei comandi utilizzabili e una breve descrizione del loro funzionamento:
<br>
<ul>
<li>Al comando **/help** o invocando l'app con flag _--help_ o _-h_ il risultato è una descrizione concisa, che normalmente appare all'avvio del programma, seguita dalla lista di comandi disponibili, uno per riga, come da esempio successivo:<br>
<ul>
<li>	gioca<br></li>
<li>	esci<br></li>
</ul>
</li><br>
<li>Al comando <strong>/esci</strong> l’applicazione risponde visualizzando il livello di gioco e il numero di massimo di tentativi falliti<br>
<ul>
<li>	se la conferma è positiva, l'applicazione si chiude restituendo il controllo al sistema operativo</li>
<li>	se la conferma è negativa, l'applicazione si predispone a ricevere nuovi tentativi o comandi</li>
</ul>
</li><br>
<li>Al comando <strong>/facile</strong> l’applicazione risponde con OK e imposta a 50 il numero massimo di tentativi falliti.</li><br>

<li>Al comando <strong>/medio</strong> l’applicazione risponde con OK e imposta a 30 il numero massimo di tentativi falliti.</li><br>

<li>Al comando <strong>/difficile</strong> l’applicazione risponde con OK e imposta a 10 il numero massimo di tentativi falliti.</li><br>

<li>Al comando <strong>/mostralivello</strong> l’applicazione risponde visualizzando il livello di gioco e il numero di massimo di tentativi falliti.</li><br>

<li>Al comando <strong>/mostranavi</strong> l’applicazione risponde visualizzando, per ogni tipo di nave, la dimensione in quadrati e il numero di esemplari da affondare:<br>
<ul>
<li>	Cacciatorpediniere 	⊠⊠ 		esemplari: 4 </li>
<li>	Incrociatore 		⊠⊠⊠ 		esemplari: 3 </li>
<li>	Corazzata 		⊠⊠⊠⊠ 		esemplari: 2 </li>
<li>	Portaerei  		⊠⊠⊠⊠⊠ 		esemplari: 1 </li>
</ul>
</li><br>
<li>Al comando <strong>/gioca</strong> se nessuna partita è in corso l'applicazione imposta causalmente le navi, in orizzontale o in verticale, mostra la griglia vuota e si predispone a ricevere il primo tentativo o altri comandi.</li><br>

<li>Al comando <strong>/svelagriglia</strong> l’applicazione risponde visualizzando, una griglia 10x10, con le righe numerate da 1 a 10 e le colonne numerate da A a J, e tutte le navi posizionate.</li><br>
</ul> 

[Torna all'indice](#indice)

## 9 Analisi retrospettiva

### 9.1 Sprint 0
   
![Retrospective_Mad_Sad_Glad_Template](./img/Retrospective_Mad_Sad_Glad_Template.jpg)

[Torna all'indice](#indice)