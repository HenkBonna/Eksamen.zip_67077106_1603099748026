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
    // oppgave 1 - finished
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

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
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
    // Oppgave 3 ii) finished
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
    // Oppgave 4 i) untested
    public void postorden(Oppgave<? super T> oppgave) {
        // Skal løses ikke-rekursivt, og skal bruke nestePostorden
        Node<T> current = rot;
        // Gjør 'oppgave' på rot
        oppgave.utførOppgave(current.verdi);
        // Hopper ett steg – gjør dette før whileLøkken, slik at

        current = nestePostorden(current);
        assert current != null;
        while(current.forelder != null){
            oppgave.utførOppgave(current.verdi);
            current = nestePostorden(current);
        }
        // Gjør oppgaven på siste node
        oppgave.utførOppgave(current.verdi);

    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }
    // Oppgave 4 ii)
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        // 1. Call itself
        // 2. Simplify arguments
        // 3. Change in base-case

        // Første
        oppgave.utførOppgave(p.verdi);

        if(p.forelder != null){
            postordenRecursive(nestePostorden(p),oppgave);
        }

        if(p.forelder == null){

        }


    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
