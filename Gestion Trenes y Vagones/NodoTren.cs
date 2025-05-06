internal class NodoTren
{

        private Tren tren;
        private NodoTren siguiente;

        public Tren Tren { get =>  tren; set => tren = value; }
        public NodoTren Siguiente { get => siguiente; set => siguiente = value; }

        public NodoTren(Tren tren) {

            Tren = tren;
            Siguiente = null;

        }


    }
}
