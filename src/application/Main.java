package application;	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
class ButtonEvent implements EventHandler<ActionEvent>{
	ButtonEvent(Text t,Text t1){
		this.t=t;
		this.t1=t1;
	}
	static Text t;
	static Text t1;
	static String s1;
	static boolean check=false;
	static boolean checker=true;
	static boolean dchecker=false;
	static int count=-1;
    static String show;
    static double result;
		
			public void handle(ActionEvent e) {
				try {
				String s=(((Button)e.getSource()).getText());
				Expression ex = new Expression();
				if(s.equals("=")) {
					if(t.getText().equals("0")) {
						return;
					}
					String s1=t.getText();
					if(s1.charAt(count)=='%'||s1.charAt(count)=='+'||s1.charAt(count)=='-'||s1.charAt(count)=='*'||s1.charAt(count)=='/'||s1.charAt(count)=='^'||s1.charAt(count)=='(') {
						return;
					}
				     result=ex.print(show);
					show=result+"";
					show=show.replace('-','~');
					t.setText(result+"");
					t1.setText("");
					t1.setText("Last Result: "+result);
					count=t.getText().length()-1;
					checker=false;
					dchecker=false;
					return;
				}
				
			else if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")||s.equals("%")||s.equals("^")) {
				if(checker==false)
					checker=true;
				    String string=t.getText();
				    if(s.equals("-")&&(t.getText().equals("0")||t.getText().charAt(count)=='(')) {
				    	s="~";
				    	if(t.getText().equals("0")) {
				    		show="~";
				    		t.setText("-");
				    		check=true;
							checker=true;
							count++;
							return;
				    	} 	
				    	s="~";
						check=true;
						checker=true;
				     }
				    else if(check==false||string.charAt(count)=='%'||string.charAt(count)=='-'||string.charAt(count)=='+'||string.charAt(count)=='*'||string.charAt(count)=='/'||string.charAt(count)=='^'||string.charAt(count)=='(') {
						return;
					}
				     
					dchecker=false;
					show+=s;
					if(s=="~") {
						s="-";
					}
					t.setText(t.getText()+s);
				}else if(s.equals("(")){
					if(checker==false) {
						show="(";
						t.setText("(");
						checker=true;
						count=-1;
					}
					else if(t.getText().equals("0")) {
						show=s;
						t.setText(s);
						check=true;
					}
					else if(t.getText().charAt(count)=='('||t.getText().charAt(count)=='+'||t.getText().charAt(count)=='-'||t.getText().charAt(count)=='*'||t.getText().charAt(count)=='/'||t.getText().charAt(count)=='%'||t.getText().charAt(count)=='^') {
						show+=s;
						t.setText(t.getText()+s);
					}else {
						return;
					}
					check=true;
				}else if(s.equals(")")){
					if(t.getText().equals("0"))
						return;
					 if(!(t.getText().charAt(count)=='('||t.getText().charAt(count)=='+'||t.getText().charAt(count)=='-'||t.getText().charAt(count)=='*'||t.getText().charAt(count)=='/'||t.getText().charAt(count)=='%'||t.getText().charAt(count)=='^')) {
						show+=s;
						 t.setText(t.getText()+s);
					}else {
						return;
					}
				}   else if(s.equals("AC")) {
					show="0";
					t.setText("0");
					t1.setText("");
					check=false;
					dchecker=false;
					checker=true;
					count=-1;
					return;
				}else if(s.equals("clr")){
					if(t.getText().equals("")||t.getText().equals("0"))
						return;
					int n=t.getText().length();
					String s2="";
					for(int i=0;i<n-1;i++)
						s2+=t.getText().charAt(i);
					if(t.getText().charAt(n-1)=='.')
						dchecker=false;
					if(t.getText().charAt(n-1)=='/'||t.getText().charAt(n-1)=='*'||t.getText().charAt(n-1)=='+'||t.getText().charAt(n-1)=='-'||t.getText().charAt(n-1)=='%'||t.getText().charAt(n-1)=='^') {
						while(!(t.getText().charAt(n-2)=='/'||t.getText().charAt(n-2)=='*'||t.getText().charAt(n-2)=='+'||t.getText().charAt(n-2)=='-'||t.getText().charAt(n-2)=='%'||t.getText().charAt(n-2)=='^')) {
							if(t.getText().charAt(n-1)=='.') {
							dchecker=true;
							break;
							}
							n--;
							if(n-2==-1)
								break;
						}
					}
					t.setText(s2);
					if(t.getText().equals("")) {
						show="0";
						t.setText("0");
						check=false;
						dchecker=false;
						checker=true;
						}
					   count--;
					   return;
				}else {
					
					if(dchecker==true&&t.getText()!=""&&s.equals("."))
					return;
					if(t.getText()!=""&&s.equals(".")) {
						dchecker=true;
					}
					if(check==false) {
						check=true;
						show="";
						t.setText("");
					}
					if(checker==false) {
						show="";
						t.setText("");
						checker=true;
						count=-1;
					}
					if(s.equals("Ans")) {
						show+=result;
						t.setText(t.getText()+result);
						count++;
						return;
					}
					show+=s;
					t.setText(t.getText()+s);
				}
				count++;
				}catch(Exception e1) {
					show="Syntax error";
					t.setText("Syntax error");
				}
			}
	
	

	
}
public class Main extends Application {
	private Object object;

	@Override
	public void start(Stage primaryStage) {
		Text t1=new Text();
		Text t2=new Text();
		try {
			Rectangle r1=new Rectangle(20,20,240,50);
			Rectangle r2=new Rectangle(20,20,240,30);
			r1.setFill(Color.WHITE);
			t1.setText("0");
			r2.setFill(Color.WHITE);
			t2.setText("");
			t1.setFill(Color.RED);
			GridPane gp = new GridPane();	
			StackPane sp=new StackPane(r1,t1);
			StackPane sp1=new StackPane(r2,t2);
			VBox v1=new VBox();
			v1.getChildren().add(sp1);
			v1.getChildren().add(sp);
			v1.getChildren().add(gp);
			sp.setPadding(new Insets(30,0,0,10));
			Scene scene = new Scene(v1,350,400);
			sp1.setPadding(new Insets(30,0,5,5));
			sp.setPadding(new Insets(0,0,5,5));
			Button AC=new Button("AC");
			Button clr=new Button("clr");
			Button ans=new Button("Ans");
			Button b0=new Button("0");
			Button b1=new Button("1");
			Button b2=new Button("2");
			Button b3=new Button("3");
			Button b4=new Button("4");
			Button b5=new Button("5");
			Button b6=new Button("6");
			Button b7=new Button("7");
			Button b8=new Button("8");
			Button b9=new Button("9");
			Button add=new Button("+");
			Button sub=new Button("-");
			Button div=new Button("/");
			Button mul=new Button("*");
			Button eq=new Button("=");
			Button per=new Button("%");
			Button cb=new Button(")");
			Button dot=new Button(".");
			Button ob=new Button("(");
			Button pow=new Button("^");
			AC.setStyle("-fx-background-color:lightskyblue");
			clr.setStyle("-fx-background-color:lightskyblue");
			ans.setStyle("-fx-background-color:lightskyblue");
    		ob.setStyle("-fx-background-color:goldenrod");
			add.setStyle("-fx-background-color:goldenrod");
			div.setStyle("-fx-background-color:goldenrod");
			mul.setStyle("-fx-background-color:goldenrod");
			eq.setStyle("-fx-background-color:goldenrod");
			cb.setStyle("-fx-background-color:goldenrod");
			pow.setStyle("-fx-background-color:goldenrod");
			sub.setStyle("-fx-background-color:goldenrod");
			b8.setStyle("-fx-background-color:darkgrey");
			b9.setStyle("-fx-background-color:darkgrey");
			b7.setStyle("-fx-background-color:darkgrey");
			b6.setStyle("-fx-background-color:darkgrey");
			b5.setStyle("-fx-background-color:darkgrey");
			b4.setStyle("-fx-background-color:darkgrey");
			b3.setStyle("-fx-background-color:darkgrey");
			b2.setStyle("-fx-background-color:darkgrey");
			b1.setStyle("-fx-background-color:darkgrey");
			b0.setStyle("-fx-background-color:darkgrey");
			dot.setStyle("-fx-background-color:darkgrey");
			per.setStyle("-fx-background-color:darkgrey");

			
			Button arr[]= {ob,b7,b4,b1,per,div,b8,b5,b2,b0,mul,b9,b6,b3,dot,cb,sub,add,pow,eq};
			
			for(int i=0;i<20;i++) {
				(arr[i]).setPrefSize(40, 30);
				arr[i].setOnAction(new ButtonEvent(t1,t2));
				arr[i].setTextFill(Color.BLACK);
				
			}
			AC.setPrefSize(40, 30);
			AC.setOnAction(new ButtonEvent(t1,t2));
			clr.setPrefSize(40, 30);
			clr.setOnAction(new ButtonEvent(t1,t2));
			ans.setPrefSize(40, 30);
			ans.setOnAction(new ButtonEvent(t1,t2));
			gp.add(AC,0,0);
			gp.add(clr,1,0);
			gp.add(ans,2,0);
			int count=0;
			for(int i=0;i<4;i++) {
				for(int j=1;j<=5;j++) {
					gp.add(arr[count], i, j);
					count++;
				}
			}
			
			gp.setHgap(25);
			gp.setVgap(5);
			gp.setPadding(new Insets(scene.getHeight()/20,0,5,scene.getHeight()/6.5));
		
			primaryStage.setScene(scene);
			primaryStage.setTitle("My Calculator");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			t1.setText("Expression error");
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}