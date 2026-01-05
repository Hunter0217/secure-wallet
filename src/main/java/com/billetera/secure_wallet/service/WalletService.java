package com.billetera.secure_wallet.service;

import com.billetera.secure_wallet.entity.Wallet;
import com.billetera.secure_wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor // Lombok crea un constructor automático para conectar el repositorio
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }


    public List<Wallet> getAllWallets() {
        return walletRepository.findAll(); // Leemos de BD
    }


    public Wallet deposit(Long id, Double amount) {
        if (amount <= 0) {
            throw new RuntimeException("El monto debe ser mayor a 0 " + amount);
        }

        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡Billetera no encontrada!"));

        Double newBalance = wallet.getBalance() + amount;
        wallet.setBalance(newBalance);

        return walletRepository.save(wallet);
    }


    public Wallet withdraw(Long id, Double amount) {
        // 1. Validar que el monto sea positivo
        if (amount <= 0) {
            throw new RuntimeException("¡El monto debe ser mayor a 0.");
        }

        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));

        if (wallet.getBalance() < amount) {
            throw new RuntimeException("Saldo insuficiente para realizar el retiro. ");
        }

        Double newBalance = wallet.getBalance() - amount;
        wallet.setBalance(newBalance);

        return walletRepository.save(wallet);
    }
}