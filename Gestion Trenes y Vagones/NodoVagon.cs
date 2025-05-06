internal class NodoVagon
{

        private Vagon vagon;
        private NodoVagon siguiente;

        public Vagon Vagon { get => vagon; set => vagon = value; }
        public NodoVagon Siguiente { get => siguiente; set => siguiente = value; }

        public NodoVagon(Vagon vagon)
        {
            Vagon = vagon;
            Siguiente = null;
        }

    }
