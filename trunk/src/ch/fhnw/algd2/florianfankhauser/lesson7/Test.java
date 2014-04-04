// Created by Florian Fankhauser on 04.04.2014

package ch.fhnw.algd2.florianfankhauser.lesson7;

public class Test {
	public static void main(String[] args) {
		String[] chars = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,E,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,w,r,s,t,u,v,w,x,y,z,1,2,3,4,5,6,7,8,9,0".split(",");
//		String[] chars = "A,B,1,2,3,4,5,6,7,8,9,0".split(",");
		double counter = 0;
		int tests = 100;
		for (int t = 0; t < tests; t++) {
			HashMap map = new HashMap();
			try {
				while (true) {
					StringBuilder b = new StringBuilder();
					for (int i = 0; i < Math.random() * 100; i++) {
						b.append(chars[(int) (Math.random() * chars.length)]);
					}
					map.put(b.toString(), "TestValue");
					counter++;
				}
			} catch (IllegalStateException e) {
			}
		}
		System.out.println("Average puts without duplicate of " + tests + " testruns: " + (counter / tests));
	}
}
