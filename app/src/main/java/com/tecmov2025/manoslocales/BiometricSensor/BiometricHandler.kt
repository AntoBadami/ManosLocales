package com.tecmov2025.manoslocales.BiometricSensor

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executor

object BiometricHandler {

    private var baseActivity: FragmentActivity? = null




    fun canAuthenticate(context: Context): Boolean {
        val bm = BiometricManager.from(context)
        val flags = BiometricManager.Authenticators.BIOMETRIC_STRONG or
                BiometricManager.Authenticators.DEVICE_CREDENTIAL

        return bm.canAuthenticate(flags) in listOf(
            BiometricManager.BIOMETRIC_SUCCESS,
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED
        )
    }

    fun setBaseActivity(act: FragmentActivity)
    {
        baseActivity = act
    }

    fun requireAuth(onSuccess: () -> Unit,
                    onError: (String, Int) -> Unit,
                    onFailure: () -> Unit={}){
        val currentBaseActivity = baseActivity
        if(currentBaseActivity != null)
        {
            val executor: Executor = ContextCompat.getMainExecutor(currentBaseActivity)
            val prompt = BiometricPrompt(
                currentBaseActivity, executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        onSuccess()

                    }
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        onError(errString.toString(),errorCode)
                    }
                    override fun onAuthenticationFailed() {
                        onFailure()
                    }
                }
            )
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticación requerida")
                .setSubtitle("Usa huella o PIN para continuar")
                .setAllowedAuthenticators(
                    BiometricManager.Authenticators.BIOMETRIC_STRONG or
                            BiometricManager.Authenticators.DEVICE_CREDENTIAL
                )
                .build()

            prompt.authenticate(promptInfo)

        }

    }

}
