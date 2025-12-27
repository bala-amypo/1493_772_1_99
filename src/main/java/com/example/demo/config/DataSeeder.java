package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AssetRepository assetRepo;
    private final VendorRepository vendorRepo;
    private final DepreciationRuleRepository ruleRepo;
    private final RoleRepository roleRepo;

    public DataSeeder(AssetRepository assetRepo,
                      VendorRepository vendorRepo,
                      DepreciationRuleRepository ruleRepo,
                      RoleRepository roleRepo) {
        this.assetRepo = assetRepo;
        this.vendorRepo = vendorRepo;
        this.ruleRepo = ruleRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public void run(String... args) {

        /* ---------------- ROLES ---------------- */

        if (roleRepo.findByName("ADMIN").isEmpty()) {
            roleRepo.save(new Role("ADMIN"));
        }

        if (roleRepo.findByName("USER").isEmpty()) {
            roleRepo.save(new Role("USER"));
        }

        /* ---------------- VENDOR ---------------- */

        Vendor vendor = vendorRepo.findByVendorName("StaticVendor")
                .orElseGet(() -> {
                    Vendor v = new Vendor();
                    v.setVendorName("StaticVendor");
                    v.setContactEmail("static@example.com");
                    v.setPhone("9999999999");
                    return vendorRepo.save(v);
                });

        /* ---------------- DEPRECIATION RULE ---------------- */
        // 🔥 FIXED: Consistent unique name → prevents duplicate key warning

        DepreciationRule rule = ruleRepo.findByRuleName("IntegrationRule")
                .orElseGet(() -> {
                    DepreciationRule r = new DepreciationRule();
                    r.setRuleName("IntegrationRule");
                    r.setMethod("STRAIGHT_LINE");
                    r.setUsefulLifeYears(5);
                    r.setSalvageValue(0.0);
                    return ruleRepo.save(r);
                });

        /* ---------------- ASSET ---------------- */

        if (!assetRepo.existsByAssetTag("INTEG-TAG-001")) {
            Asset asset = new Asset();
            asset.setAssetTag("INTEG-TAG-001");
            asset.setAssetName("ExistingAsset");
            asset.setVendor(vendor);
            asset.setDepreciationRule(rule);
            asset.setPurchaseCost(1000.0);
            asset.setPurchaseDate(LocalDate.now());
            asset.setStatus("ACTIVE");
            assetRepo.save(asset);
        }
    }
}
