package no.oslomet.cs.algdat.Eksamen;


import java.util.*;

public class EksamenSBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public EksamenSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }
    // Oppgave 1 - finished
    public boolean leggInn(T verdi) {

        // Kode hentet fra Programkode 5.2.3 a), fra kompendium av Ulf Utterud

        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node // q som forelder

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging


    }
    // Oppgave 6 i) IKKE gjort noen endringer enda. Foreldrepeker,
    public boolean fjern(T verdi) {
        // Kopiert fra kompendium; Programkode 5.2.8 d)

        if (verdi == null) return false;  // treet har ingen nullverdier

        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) rot = b;
            else if (p == q.venstre) {
                q.venstre = b;
                b.forelder = q.forelder; // HENRIK
            }
            else {
                q.høyre = b;
                b.forelder = q.forelder; // HENRIK
            }
        }
        else  // Tilfelle 3)
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) {
                s.forelder = r.forelder;
                s.venstre = r.høyre;
            }
            else {
                s.forelder = r.forelder;
                s.høyre = r.høyre;
                //s.venstre.forelder = s; // HENRIK
            }
        }

        antall--;   // det er nå én node mindre i treet
        return true;
    }
    // Oppgave 6 ii) Ikke gjort
    public int fjernAlle(T verdi) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        // Teller hvor mange fjernelser som gjøres
        int count = 0;
        // Gjør fjerning av verdi helt til man ikke lenger finner verdien som vil fjernes.
        while(fjern(verdi)){
            fjern(verdi);
            count++;
        }
        // Returnerer telleren.
        return count;
    }
    // Oppgave 2 – needs more Main-tests
    public int antall(T verdi) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        int count = 0;

        Node<T> current = rot;

        while (current != null){
            // comparing the values
            int compRes = comp.compare(verdi, current.verdi);

            // if the comparison returns that verdi < current.verdi, we go left
            if (compRes < 0){
                current = current.venstre; // iterate downwards
            }
            // if the comparison returns that verdi >= current.verdi, we go right
            if (compRes >= 0){
                if (current.verdi == verdi) count++;    // we check if the current val is equal, if so add to count
                current = current.høyre;    // iterate down
            }
        }

        return count;

    }
    // Oppgave 6 ii)
    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    // Oppgave 3 i) finished
    private static <T> Node<T> førstePostorden(Node<T> p) {

        // Kildekode hentet fra Kompendium: Programkode 5.1.7 h)
        while (true){
            if (p.venstre != null) p = p.venstre;
            else if(p.høyre != null) p = p.høyre;
            else return p;
        }
    }
    // Oppgave 3 ii) thought finished, unsure if working...
    private static <T> Node<T> nestePostorden(Node<T> p) {
        // "Oversatt" beskrivelse fra kompendium til kode
        // Sjekker først om forelderen er null, da er vi på siste, og neste fins ikke.
        if (p.forelder != null) {
            // Hvis p er høyre barn, er forelderen neste.
            if (p.forelder.høyre == p) return p.forelder;
            // Hvis p er venstre barn
            else if(p.forelder.venstre == p){
                // Hvis venstrebarn er enebarn, er forelder neste
                if (p.forelder.høyre == null) return p.forelder;
                // Ellers er neste førstePostOrden til forelders høyrebarn
                else return førstePostorden(p.forelder.høyre);
            }
        }
        // Hvis alle over feiler, er forelder lik 'null', så vi har ingen neste.
        return null;
    }
    // Oppgave 4 i) seems to be working
    public void postorden(Oppgave<? super T> oppgave) {


        //throw new UnsupportedOperationException("Ikke kodet ennå!");

        // Skal løses ikke-rekursivt, og skal bruke nestePostorden

        // Finner startNoden, vha. førstePostOrden
        Node<T> current = førstePostorden(rot);

        // Gjør 'oppgave' på førstePostorden
        oppgave.utførOppgave(current.verdi);

        // While løkke som utføres frem til den når rot-noden (node uten forelder)
        while (current.forelder != null) {
            current = nestePostorden(current);
            oppgave.utførOppgave(current.verdi);
        }


        // Utfører oppgaven på rotnoden. REMOVING THIS SEEMS TO FIX IT
        //oppgave.utførOppgave(current.verdi);


            // Gjør oppgaven på siste node


    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }
    // Oppgave 4 ii) seems to be working
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");

        // Hvis vi befinner oss i rot, rekurserer vi med førstePostorden.
        if (p.forelder == null){
            postordenRecursive(førstePostorden(p), oppgave);
        }

        else {
            // Utfører oppgaven.
            oppgave.utførOppgave(p.verdi);
            // Dersom neste ikke er rot, rekurserer vi.
            if (nestePostorden(p) != rot) {
                postordenRecursive(nestePostorden(p), oppgave);
            }
            // Ellers befinner vi oss på nest-siste node. Har allerede har utført oppgaven for denne.
            else {
                // Mangler derfor bare å utføre oppgaven på siste node (rot-noden) før vi kan slutte
                oppgave.utførOppgave(rot.verdi);
            }

        }


        /* Working, but looping after doing it.
        if(p.forelder != null){
            oppgave.utførOppgave(p.verdi);
            postordenRecursive(nestePostorden(p),oppgave);
        }

        else{
            postordenRecursive(førstePostorden(p),oppgave);
            //oppgave.utførOppgave(p.verdi);
        }
         */

        /* Working, but looping after doing it.
        if(p.forelder == null){
            postordenRecursive(førstePostorden(p),oppgave);
            //oppgave.utførOppgave(p.verdi);
        }

        else{
            oppgave.utførOppgave(p.verdi);
            postordenRecursive(nestePostorden(p),oppgave);
        }

         */




    }
    // 5i) seeems to be working fine. Remove SysOut
    public ArrayList<T> serialize() {
        // Denne oppgaven ble løst ved å se notater fra en av forelesningene, som omhandlet in-, pre- og postoreden.

        // Initialiserer ArrayListen
        ArrayList<T> outArray = new ArrayList<>();
        // Vi bruker en ArrayDeque, som lagrer noder i en dobbeltsidig kø
        ArrayDeque<Node<T>> dq = new ArrayDeque<>();
        // Legger til roten først i køen
        dq.addFirst(rot);
        // Looper gjennom deque'en helt til den er tom <=> alle elementene er gjennomgått
        while (!dq.isEmpty()){
            // Tar den første fra deque'en, og setter den som current.
            Node<T> current = dq.removeFirst();
            // Dersom venstre ikke er null, legger vi først den til deque'en
            if (current.venstre != null) {
                dq.addLast(current.venstre);
            }
            // Dersom høyre ikke er null, legger vi deretter den til deque'en
            if (current.høyre != null) {
                dq.addLast(current.høyre);
            }
            // Legger verdien til current i ArrayList'a
            outArray.add(current.verdi);
        }
        // debug
        System.out.println();
        for (T node : outArray){
            System.out.print(node + ", ");
        }
        System.out.println();

        ///
        return outArray;
    }
    // 5ii) Seems to be working – maybe run a few more tests. Remove SysOut
    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        EksamenSBinTre<K> out = new EksamenSBinTre<K>(c);
        //out.rot = new Node<K>(data.get(0), null, null, null);
        for (K value : data){
            out.leggInn(value);
        }
        return out;
    }


} // ObligSBinTre
