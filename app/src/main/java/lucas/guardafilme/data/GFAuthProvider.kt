package lucas.guardafilme.data

import android.support.v4.app.FragmentActivity
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import javax.inject.Inject

/**
 * Created by lucassantos on 21/10/17.
 */
class GFAuthProvider @Inject constructor(): AuthProvider {
    override fun loginUser(activity: FragmentActivity) {

    }

    override fun logoutUser(activity: FragmentActivity, completionListener: () -> Unit) {
        AuthUI.getInstance()
                .signOut(activity)
                .addOnCompleteListener { task -> completionListener() }
    }
}