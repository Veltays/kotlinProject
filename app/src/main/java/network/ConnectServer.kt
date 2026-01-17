package network

import ProtocoleCAP.Reponse.Reponse_SEARCH_CONSULTATIONS
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import protocol.Requete
import protocol.Reponse
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import model.entity.Consultation

object ConnectServer {

    private const val HOST = "192.168.1.10"
    private const val PORT = 6769

    private var idConnexionWithServer: Int? = null

    // =====================================================
    // MÉTHODE GÉNÉRIQUE D’ENVOI
    // =====================================================

    private suspend fun sendRequest(requete: Requete): Reponse? {
        return withContext(Dispatchers.IO) {
            try {
                Socket(HOST, PORT).use { socket ->
                    val oos = ObjectOutputStream(socket.getOutputStream())
                    val ois = ObjectInputStream(socket.getInputStream())

                    // Injection de l'id de connexion
                    requete.setIdConnexion(idConnexionWithServer)

                    oos.writeObject(requete)
                    oos.flush()

                    ois.readObject() as Reponse
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    // =====================================================
    // LOGIN
    // =====================================================

    suspend fun login(requete: Requete): Boolean {
        val response = sendRequest(requete)

        if (response is ProtocoleCAP.Reponse.Reponse_LOGIN && response.isValide()) {
            idConnexionWithServer = response.getIdConnexion()
            return true
        }
        return false
    }

    // =====================================================
    // LOGOUT
    // =====================================================

    suspend fun logout(): Boolean {
        val requete = ProtocoleCAP.Requete.Requete_LOGOUT()
        val response = sendRequest(requete)

        return if (response is ProtocoleCAP.Reponse.Reponse_LOGOUT && response.isValide()) {
            idConnexionWithServer = null
            true
        } else {
            false
        }
    }


    // =====================================================
    // ADD PATIENT
    // =====================================================


    suspend fun addPatient(requete: Requete): Int {
        val response = sendRequest(requete)

        return if (response is ProtocoleCAP.Reponse.Reponse_ADD_PATIENT) {
            response.idAttribuer
        } else {
            -1
        }
    }


    // =====================================================
    // ADD CONSULTATION
    // =====================================================



    suspend fun addConsultation(requete: Requete): Boolean {
        val response = sendRequest(requete)
        return response is ProtocoleCAP.Reponse.Reponse_ADD_CONSULTATION && response.isValide()
    }


    ////////////
    //// search consultation
    ////////////////////////////////

    suspend fun searchConsultation(requete: Requete): List<Consultation> {
        val response = sendRequest(requete)

        if (response is Reponse_SEARCH_CONSULTATIONS && response.isValide()) {
            Log.e("DEBUG", "SIZE = ${response.consultationsList.size}")
            return response.consultationsList
        }

        return emptyList()
    }

    ///////////
    // DELETE
    //////////

    suspend fun deleteConsultation (requete : Requete): Boolean {
        val response = sendRequest(requete)
        return response is ProtocoleCAP.Reponse.Reponse_DELETE_CONSULTATION && response.isValide()
    }

    /////// ////////
    /// UPDATE
    ///////////

//    suspend fun updateConsultation (requete : Requete) : Boolean {
//        val response = sendRequest(requete)
//        //return response is ProtocoleCAP.Reponse.Reponse_UPDATE_CONSULTATION && response.isValide()
//    }



}
