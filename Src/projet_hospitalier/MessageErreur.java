package projet_hospitalier;
/**
 * cette classe s'occupe des messages d'erreurs a afficher en cas de non respects
 * d'une contrainte
 */
public class MessageErreur {
	 private int idMessage;
	 private String message;
        // les constructeurs
	    public MessageErreur() {}

	    public MessageErreur(int idMessage, String message) {
	        this.idMessage = idMessage;
	        this.message = message;
	    }
        /**
         * Renvoie le id du message
         * @return
         */
	        public int getIdMessage() {
	        return idMessage;
	    }
         /**
          * Modifie le id du  message 
          * @param idMessage
          */
	    public void setIdMessage(int idMessage) {
	        this.idMessage = idMessage;
	    }
/**
 * Renvoie le message 
 * @return une chaine de caractere contenu du message
 */
	    public String getMessage() {
	        return message;
	    }
/**
 * Modifie le message d'erreur a la chaine eneparametre
 * @param message
 */
	    public void setMessage(String message) {
	        this.message = message;
	    }

}
