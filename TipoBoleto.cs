public enum TipoBoletoEnum
 {
     Premium, Ejecutivo, Estandar
 }

 internal class TipoBoleto {

     private bool prioridadEmbarque;
     private TipoBoletoEnum tipo; 

     public bool PrioridadEmbarque { get => prioridadEmbarque; set => prioridadEmbarque = value; }
     public TipoBoletoEnum Tipo { get => tipo; set => tipo = value; }

     
     public TipoBoleto(TipoBoletoEnum tipo, bool prioridadEmbarque) {

         Tipo = tipo;
         PrioridadEmbarque = prioridadEmbarque;
     }


     public TipoBoletoEnum SeleccionarTipoBoleto(string tipoBoleto) { 

         switch (tipoBoleto.ToLower()) 
         {
             case "premium":
                 return TipoBoletoEnum.Premium;

             case "ejecutivo":
                 return TipoBoletoEnum.Ejecutivo;

             case "estandar":
                 return TipoBoletoEnum.Estandar;

             default:
                 throw new ArgumentException("Tipo de boleto no válido.");
         }
     }


 }