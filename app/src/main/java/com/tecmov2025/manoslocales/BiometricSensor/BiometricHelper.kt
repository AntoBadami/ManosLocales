package com.tecmov2025.manoslocales.BiometricSensor

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executor

object BiometricHelper {

    fun canAuthenticate(context: Context): Boolean {
        val bm = BiometricManager.from(context)
        val flags = BiometricManager.Authenticators.BIOMETRIC_STRONG or
                BiometricManager.Authenticators.DEVICE_CREDENTIAL

        return bm.canAuthenticate(flags) in listOf(
            BiometricManager.BIOMETRIC_SUCCESS,
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED
        )
    }

    fun createPrompt(
        activity: FragmentActivity,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
        onFailure: () -> Unit
    ): BiometricPrompt {
        val executor: Executor = ContextCompat.getMainExecutor(activity)
        return BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    onSuccess()
                }
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    onError(errString.toString())
                }
                override fun onAuthenticationFailed() {
                    onFailure()
                }
            }
        )
    }

    fun buildPromptInfo(): BiometricPrompt.PromptInfo =
        BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación requerida")
            .setSubtitle("Usa huella o PIN para continuar")
            .setDeviceCredentialAllowed(true)
            .build()
}
