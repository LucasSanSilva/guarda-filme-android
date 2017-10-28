package lucas.guardafilme.ui

import android.support.v7.app.AppCompatActivity

/**
 * Created by lucassantos on 28/10/17.
 */
abstract class BaseActivity: AppCompatActivity() {
    abstract fun showLoading()
    abstract fun hideLoading()
}