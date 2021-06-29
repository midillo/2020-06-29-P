package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {

	private PremierLeagueDAO dao;
	private SimpleWeightedGraph<Match, DefaultWeightedEdge> grafo;
	private Map<Integer, Match> idMap;
	private List<Adiacenze> archi;


	public Model() {
		this.dao = new PremierLeagueDAO();
		this.idMap = new HashMap<Integer, Match>();
		this.archi = new ArrayList<Adiacenze>();
	}


	public String creaGrafo(int mese, int min) {

		this.grafo = new SimpleWeightedGraph<Match, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		dao.getMatchesByMonth(mese, idMap);
		Graphs.addAllVertices(this.grafo, idMap.values());

		archi = dao.getAdiacenze(mese, min);
		for(Adiacenze a: archi) {
			if(idMap.containsKey(a.getM1()) && idMap.containsKey(a.getM2())) {
				Graphs.addEdge(this.grafo, idMap.get(a.getM1()), idMap.get(a.getM2()), a.getPeso());
			}
		}

		if(this.grafo==null) {
			return null;
		}

		return "Possiede: " +this.grafo.vertexSet().size()+ " vertici e " +this.grafo.edgeSet().size()+ " archi.\n";
	}

	public String connessioneMax(){

		String result = "";
		Double max = 0.00;
		
		for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)>max) {
				max = this.grafo.getEdgeWeight(e);
			}
		}
		
		for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)==max) {
				result += this.grafo.getEdgeSource(e)+"\t"+this.grafo.getEdgeTarget(e)+"\t("+this.grafo.getEdgeWeight(e)+")\n";
			}
		}
		
		return result;
	}
	
	public boolean esistenzaGrafo() {
		if(this.grafo==null) {
			return false;
		}else
			return true;
	}

	public List<Match> getAllVertex(){
		List<Match> result = new ArrayList<Match>(this.idMap.values());
		Collections.sort(result);
		return result;
	}

	public void camminoMassimo(Match m1, Match m2) {
	
		
		
	}
}
