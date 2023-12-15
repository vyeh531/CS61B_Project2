package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;


import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wn;
    private NGramMap ngm;
    public HyponymsHandler(WordNet wn, NGramMap ngm) {
        this.wn = wn;
        this.ngm = ngm;
    }
    @Override
    public String handle(NgordnetQuery q) {
        Integer k = q.k();
        List<String> words = q.words();
        if (words.size() == 0) {
            return "[]";
        }
        int startYear = q.startYear();
        int endYear = q.endYear();
        Set<String> finalAnswer = new TreeSet<>();
        Set<String> intersection = new TreeSet<>();
        boolean first = true;
        for (String i:words) {
            Set<String> current;
            current = this.wn.hyponyms(i);

            if (first) {
                intersection.addAll(current);
                first = false;
            } else {
                intersection.retainAll(current);
            }
        }


        if (k == 0) {
            return intersection.toString();
        }

        if (k != 0) {
            Map<Double, String> ntss = new HashMap<>();
            for (String wordss : intersection) {
                TimeSeries ts = this.ngm.countHistory(wordss, startYear, endYear);
                Double values = 0.0;
                for (Double value : ts.data()) {
                    values += value;
                }
                if (values > 0) {
                    ntss.put(values, wordss);
                }
            }

            List<Double> max = new ArrayList<>();
            for (Double eachItems: ntss.keySet()) {
                max.add(eachItems);
            }
            if (max.size() == 0) {
                return "[]";
            } else {
                Collections.sort(max);
                if (max.size() > k) {
                    max = max.subList(max.size() - k, max.size());
                }
                for (Double i : max) {
                    finalAnswer.add(ntss.get(i));
                }
            }
        }
        return finalAnswer.toString();
    } }











