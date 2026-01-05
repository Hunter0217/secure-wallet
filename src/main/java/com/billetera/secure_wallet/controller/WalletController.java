package com.billetera.secure_wallet.controller;

import com.billetera.secure_wallet.entity.Wallet;
import com.billetera.secure_wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public Wallet createWallet(@RequestBody Wallet wallet) {
        return walletService.createWallet(wallet);
    }

    @GetMapping
    public List<Wallet> getAllWallets() {
        return walletService.getAllWallets();
    }

    @PutMapping("/{id}/deposit")
    public Wallet depositMoney(@PathVariable Long id, @RequestParam Double amount) {
        return walletService.deposit(id, amount);
    }

    @PutMapping("/{id}/withdraw")
    public Wallet withdrawMoney(@PathVariable Long id, @RequestParam Double amount) {
        return walletService.withdraw(id, amount);
    }
}