package ui.login

import ProtocoleCAP.Requete.Requete_LOGIN
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import network.ConnectServer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import be.kotlinprojet.R
import android.widget.Button
import android.widget.EditText
import android.widget.CheckBox
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // On charge le layout XML
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // On récupère les éléments du layout
        val loginField = view.findViewById<EditText>(R.id.LoginField)
        val passwordField = view.findViewById<EditText>(R.id.PasswordField)
        val loginButton = view.findViewById<Button>(R.id.LoginButton)
        val newUserCheckBox = view.findViewById<CheckBox>(R.id.checkBox)

        // Click sur le bouton
        loginButton.setOnClickListener {

            // récuperation des valeurs
            val login = loginField.text.toString()
            val password = passwordField.text.toString()
            val isNewUser = newUserCheckBox.isChecked

            Log.d("LoginFragment", "$login / $password / newUser=$isNewUser")



            // Création de la requête protocole
            val requeteLogin = Requete_LOGIN(
                login,
                password,
                newUser = isNewUser
            )


            lifecycleScope.launch {
                val success = ConnectServer.login(requeteLogin)

                if (success) {
                    Log.d("LoginFragment", "Connexion réussie")

                    findNavController().navigate(
                        R.id.action_loginFragment_to_mainFragment
                    )

                } else {
                    Log.d("LoginFragment", "Connexion refusée")
                }
            }
        }

        return view
    }
}
