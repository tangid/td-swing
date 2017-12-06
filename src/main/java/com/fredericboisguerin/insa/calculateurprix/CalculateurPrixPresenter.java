package com.fredericboisguerin.insa.calculateurprix;

import java.math.BigDecimal;

public class CalculateurPrixPresenter {
    private final CalculateurPrixView calculateurPrixView;

    public CalculateurPrixPresenter(CalculateurPrixView calculateurPrixView) {
        this.calculateurPrixView = calculateurPrixView;
    }

    public float onComputeButtonClicked(String montantArticleAsText, String quantiteArticle) {
        float prix = Float.parseFloat(montantArticleAsText);
        float quantite = Float.parseFloat(quantiteArticle);;
        return prix*quantite;
    }

    public float getMontantTTC(String montantHT) {
        float prix = Float.parseFloat(montantHT);
        return (float) (prix*1.2);
    }
}
