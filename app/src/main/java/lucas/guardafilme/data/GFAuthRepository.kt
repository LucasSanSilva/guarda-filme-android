package lucas.guardafilme.data

import android.arch.lifecycle.LiveData
import com.firebase.ui.auth.AuthUI
import lucas.guardafilme.model.User

/**
 * Created by lucassantos on 21/10/17.
 */
class GFAuthRepository(): AuthRepository {
    override fun loginUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun logoutUser() {

    }

    override fun getCurrentUser(): LiveData<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}