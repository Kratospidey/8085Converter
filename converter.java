// Purpose: Convert 8085 assembly instructions to machine code

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;

public class converter {

    static final Map<String, String> map = new HashMap<String, String>();

    // Define ANSI color codes
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m"; // Changed to yellow

    static void initialise() {
        // Your existing initialise method code...

        map.put("NOP","00");
        map.put("LXI B","01");
        map.put("STAX B","02");
        map.put("INX B","03"); 
        map.put("INR B","04");
        map.put("DCR B","05");
        map.put("MVI B","06");
        map.put("RLC","07");
        // map.put("NOP","08");
        map.put("DAD B","09");
        map.put("LDAX B","0A");
        map.put("DCX B","0B");
        map.put("INR C","0C");
        map.put("DCR C","0D");
        map.put("MVI C","0E");
        map.put("RRC","0F");
        // map.put("NOP","10");
        map.put("LXI D","11");
        map.put("STAX D","12");
        map.put("INX D","13");
        map.put("INR D","14");
        map.put("DCR D","15");
        map.put("MVI D","16");
        map.put("RAL","17");
        // map.put("NOP","18");
        map.put("DAD D","19");
        map.put("LDAX D","1A");
        map.put("DCX D","1B");
        map.put("INR E","1C");
        map.put("DCR E","1D");
        map.put("MVI E","1E");
        map.put("RAR","1F");
        map.put("RIM","20");
        map.put("LXI H","21");
        map.put("SHLD","22");
        map.put("INX H","23");
        map.put("INR H","24");
        map.put("DCR H","25");
        map.put("MVI H","26");
        map.put("DAA","27");
        // map.put("NOP","28");
        map.put("DAD H","29");
        map.put("LHLD","2A");
        map.put("DCX H","2B");
        map.put("INR L","2C");
        map.put("DCR L","2D");
        map.put("MVI L","2E");
        map.put("CMA","2F");
        map.put("SIM","30");
        map.put("LXI SP","31");
        map.put("STA","32");
        map.put("INX SP","33");
        map.put("INR M","34");
        map.put("DCR M","35");
        map.put("MVI M","36");
        map.put("STC","37");
        // map.put("NOP","38");
        map.put("DAD SP","39");
        map.put("LDA","3A");
        map.put("DCX SP","3B");
        map.put("INR A","3C");
        map.put("DCR A","3D");
        map.put("MVI A","3E");
        map.put("CMC","3F");
        map.put("MOV B,B","40");
        map.put("MOV B,C","41");
        map.put("MOV B,D","42");
        map.put("MOV B,E","43");
        map.put("MOV B,H","44");
        map.put("MOV B,L","45");
        map.put("MOV B,M","46");
        map.put("MOV B,A","47");
        map.put("MOV C,B","48");
        map.put("MOV C,C","49");
        map.put("MOV C,D","4A");
        map.put("MOV C,E","4B");
        map.put("MOV C,H","4C");
        map.put("MOV C,L","4D");
        map.put("MOV C,M","4E");
        map.put("MOV C,A","4F");
        map.put("MOV D,B","50");
        map.put("MOV D,C","51");
        map.put("MOV D,D","52");
        map.put("MOV D,E","53");
        map.put("MOV D,H","54");
        map.put("MOV D,L","55");
        map.put("MOV D,M","56");
        map.put("MOV D,A","57");
        map.put("MOV E,B","58");
        map.put("MOV E,C","59");
        map.put("MOV E,D","5A");
        map.put("MOV E,E","5B");
        map.put("MOV E,H","5C");
        map.put("MOV E,L","5D");
        map.put("MOV E,M","5E");
        map.put("MOV E,A","5F");
        map.put("MOV H,B","60");
        map.put("MOV H,C","61");
        map.put("MOV H,D","62");
        map.put("MOV H,E","63");
        map.put("MOV H,H","64");
        map.put("MOV H,L","65");
        map.put("MOV H,M","66");
        map.put("MOV H,A","67");
        map.put("MOV L,B","68");
        map.put("MOV L,C","69");
        map.put("MOV L,D","6A");
        map.put("MOV L,E","6B");
        map.put("MOV L,H","6C");
        map.put("MOV L,L","6D");
        map.put("MOV L,M","6E");
        map.put("MOV L,A","6F");
        map.put("MOV M,B","70");
        map.put("MOV M,C","71");
        map.put("MOV M,D","72");
        map.put("MOV M,E","73");
        map.put("MOV M,H","74");
        map.put("MOV M,L","75");
        map.put("HLT","76");
        map.put("MOV M,A","77");
        map.put("MOV A,B","78");
        map.put("MOV A,C","79");

        map.put("MOV A,D","7A");
        map.put("MOV A,E","7B");
        map.put("MOV A,H","7C");
        map.put("MOV A,L","7D");
        map.put("MOV A,M","7E");
        map.put("MOV A,A","7F");
        map.put("ADD B","80");
        map.put("ADD C","81");
        map.put("ADD D","82");
        map.put("ADD E","83");
        map.put("ADD H","84");
        map.put("ADD L","85");
        map.put("ADD M","86");
        map.put("ADD A","87");
        map.put("ADC B","88");
        map.put("ADC C","89");
        map.put("ADC D","8A");
        map.put("ADC E","8B");
        map.put("ADC H","8C");
        map.put("ADC L","8D");
        map.put("ADC M","8E");
        map.put("ADC A","8F");
        map.put("SUB B","90");
        map.put("SUB C","91");
        map.put("SUB D","92");
        map.put("SUB E","93");
        map.put("SUB H","94");
        map.put("SUB L","95");
        map.put("SUB M","96");
        map.put("SUB A","97");
        map.put("SBB B","98");
        map.put("SBB C","99");
        map.put("SBB D","9A");
        map.put("SBB E","9B");
        map.put("SBB H","9C");
        map.put("SBB L","9D");
        map.put("SBB M","9E");
        map.put("SBB A","9F");
        map.put("ANA B","A0");
        map.put("ANA C","A1");
        map.put("ANA D","A2");
        map.put("ANA E","A3");
        map.put("ANA H","A4");
        map.put("ANA L","A5");
        map.put("ANA M","A6");
        map.put("ANA A","A7");
        map.put("XRA B","A8");
        map.put("XRA C","A9");
        map.put("XRA D","AA");
        map.put("XRA E","AB");
        map.put("XRA H","AC");
        map.put("XRA L","AD");
        map.put("XRA M","AE");
        map.put("XRA A","AF");
        map.put("ORA B","B0");
        map.put("ORA C","B1");
        map.put("ORA D","B2");
        map.put("ORA E","B3");
        map.put("ORA H","B4");
        map.put("ORA L","B5");
        map.put("ORA M","B6");
        map.put("ORA A","B7");
        map.put("CMP B","B8");
        map.put("CMP C","B9");
        map.put("CMP D","BA");
        map.put("CMP E","BB");
        map.put("CMP H","BC");
        map.put("CMP L","BD");
        map.put("CMP M","BE");
        map.put("CMP A","BF");
        map.put("RNZ","C0");
        map.put("POP B","C1");
        map.put("JNZ","C2");
        map.put("JMP","C3");
        map.put("CNZ","C4");
        map.put("PUSH B","C5");
        map.put("ADI","C6");
        map.put("RST 0","C7");
        map.put("RZ","C8");
        map.put("RET","C9");
        map.put("JZ","CA");
        map.put("NOP","CB");
        map.put("CZ","CC");
        map.put("CALL","CD");
        map.put("ACI","CE");
        map.put("RST 1","CF");
        map.put("RNC","D0");

        map.put("POP D","D1");
        map.put("JNC","D2");
        map.put("OUT","D3");
        map.put("CNC","D4");
        map.put("PUSH D","D5");
        map.put("SUI","D6");
        map.put("RST 2","D7");
        map.put("RC","D8");
        map.put("NOP","D9");
        map.put("JC","DA");
        map.put("IN","DB");
        map.put("CC","DC");
        // map.put("NOP","DD");
        map.put("SBI","DE");
        map.put("RST 3","DF");
        map.put("RPO","E0");
        map.put("POP H","E1");
        map.put("JPO","E2");
        map.put("XTHL","E3");
        map.put("CPO","E4");
        map.put("PUSH H","E5");
        map.put("ANI","E6");
        map.put("RST 4","E7");

        map.put("RPE","E8");
        map.put("PCHL","E9");
        map.put("JPE","EA");
        map.put("XCHG","EB");
        map.put("CPE","EC");
        // map.put("NOP","ED");
        map.put("XRI","EE");
        map.put("RST 5","EF");
        map.put("RP","F0");
        map.put("POP PSW","F1");
        map.put("JP","F2");

        map.put("DI","F3");
        map.put("CP","F4");
        map.put("PUSH PSW","F5");
        map.put("ORI","F6");
        map.put("RST 6","F7");
        map.put("RM","F8");
        map.put("SPHL","F9");
        map.put("JM","FA");
        map.put("EI","FB");
        map.put("CM","FC");
        // map.put("NOP","FD");
        map.put("CPI","FE");
        map.put("RST 7","FF");
        
    }

    public static void main(String[] args) throws Exception {
        initialise();
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        boolean exit = true;
        while (exit) {
            // ANSI_YELLOW for prompt
            System.out.println(ANSI_YELLOW + "Enter the instruction. Type \"/Exit\" to exit" + ANSI_RESET);
            String input = br.readLine();
            input = input.toUpperCase();

            String hex = map.get(input);
            if (input.equals("/EXIT")) {
                exit = false;
            } else if (hex == null) {
                //ANSI_RED for error messages
                System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
            } else {
                //ANSI_GREEN for successful conversions
                System.out.println(ANSI_GREEN + hex + ANSI_RESET);
            }
        }
    }
}
