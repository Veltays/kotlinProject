package protocol

abstract class RequeteCAP : Requete {

    private var idConnexion: Int? = null

    override fun getIdConnexion(): Int? {
        return idConnexion
    }

    override fun setIdConnexion(idConnexion: Int?) {
        this.idConnexion = idConnexion
    }
}
