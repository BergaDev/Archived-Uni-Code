%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
function ASK(action);                                               
% Part 1 ofECTE196 Lab experiment on Signals                                                                      
%       possible actions:                                                   
%         'start'                                                           
%         'redraw'                                                          
%         'done'                                                            
%         'setfreq'
%		 'setamp'
%
%
% Modified by Christian Ritz, February 05
%
                                                                      
if nargin<1,                                                          
   action='start';                                                    
                                                                      
end;                                                                  
                                                                      
global ASK_DAT                                                       
                                                                      
if strcmp(action,'start'),                                            
                                                                      
    %====================================                             
    % Graphics initialization                                         
    oldFigNumber = watchon;                                           
    figNumber = figure;                                               
    set(gcf, ...                                                      
        'NumberTitle','off', ...                                      
        'Name','Part 1 - Amplitude Shift Keying', ...                      
        'backingstore','off',...                                      
        'Units','normalized');                                        
 
	   % Information for all buttons                                     
    labelColor=192/255*[1 1 1];                                       
    top=0.95;                                                         
    bottom=0.05;                                                      
    left=0.75;                                                        
    yInitLabelPos=0.90;                                               
    left = 0.78;                                                      
    labelWid=0.18;                                                    
    labelHt=0.05;                                                     
    btnWid = 0.18;                                                    
    btnHt=0.07;                                                       
    % Spacing between the label and the button for the same command   
    btnOffset=0.003;                                                  
    % Spacing between the button and the next command's label         
    spacing=0.05;                                                     
 %====================================                                
    % The CONSOLE frame                                               
    frmBorder=0.02;                                                   
    yPos=0.05-frmBorder;                                              
    frmPos=[left-frmBorder yPos btnWid+2*frmBorder 0.9+2*frmBorder];  
    h=uicontrol( ...                                                  
        'Style','frame', ...                                          
        'Units','normalized', ...                                     
        'Position',frmPos, ...                                        
        'BackgroundColor',[0.5 0.5 0.5]);                             
                                                                      
                                                                      
    %====================================                             
    % The SIGNAL command popup button                                 
                                                                      
    btnNumber=1;                                                      
    yLabelPos=top-(btnNumber-1)*(btnHt+labelHt+spacing);              
    labelStr=' Signal';                                               
                                                                      
    % Generic label information                                       
    labelPos=[left yLabelPos-labelHt labelWid labelHt];               
    uicontrol( ...                                                    
        'Style','text', ...                                           
        'Units','normalized', ...                                     
        'Position',labelPos, ...                                      
        'BackgroundColor',labelColor, ...                             
        'HorizontalAlignment','left', ...                             
        'String',labelStr);                                           
                                                                      
    btnPos=[left yLabelPos-labelHt-btnHt-btnOffset btnWid btnHt];     
                                                                      
popup=uicontrol('Style','Popup','String','ask|ask+noise', ...                                                                  
        'Position', btnPos, ...                                       
        'Units','normalized',...                                      
        'CallBack','ASK(''redraw'')');                               
  %====================================                               
  %====================================                               
    % The Amplitude editable text box                                  
    btnNumber=2;                                                      
    yLabelPos=top-(btnNumber-1)*(btnHt+labelHt+spacing);              
    labelPos=[left yLabelPos-labelHt labelWid labelHt];               
    lowamp_text = uicontrol( ...                                        
        'Style','text', ...                                           
        'Position', labelPos, ...                                     
        'Units','normalized', ...                                     
        'BackgroundColor',[0.5 0.5 0.5], ...                          
        'ForegroundColor','white', ...                                
        'String','Low Amplitde (V)');                                      
                                                                      
    btnPos=[left  yLabelPos-labelHt-btnHt-btnOffset ...               
            0.5*btnWid+frmBorder+0.07  btnHt];                        
    lowamp_field = uicontrol( ...                                      
        'Style','edit', ...                                           
        'Position', btnPos, ...                                       
        'Units','normalized', ...                                     
        'String',' 1',...                                             
        'CallBack','ASK(''setlowamp'')');                             
 %====================================                                
    % The High Amplitude editable text box                               
    btnNumber=3;                                                      
    yLabelPos=top-(btnNumber-1)*(btnHt+labelHt+spacing);         
    labelPos=[left yLabelPos-labelHt labelWid labelHt];               
    highamp_text = uicontrol( ...                                        
        'Style','text', ...                                           
        'Position', labelPos, ...                                     
        'Units','normalized', ...                                     
        'BackgroundColor',[0.5 0.5 0.5], ...                          
        'ForegroundColor','white', ...                                
        'String','High Amplitude (V)');                                      
                                                                      
    btnPos=[left  yLabelPos-labelHt-btnHt-btnOffset ...               
            0.5*btnWid+frmBorder+0.07  btnHt];                        
    highampf_field = uicontrol( ...                                      
        'Style','edit', ...                                           
        'Position', btnPos, ...                                       
        'Units','normalized', ...                                     
        'String',' 10',...                                             
        'CallBack','ASK(''sethighamp'')');                             
                                                                      
 %====================================                                
    % The High Amplitude editable text box                               
    btnNumber=4;                                                      
    yLabelPos=top-(btnNumber-1)*(btnHt+labelHt+spacing);         
    labelPos=[left yLabelPos-labelHt labelWid labelHt];               
    noisepower_text = uicontrol( ...                                        
        'Style','text', ...                                           
        'Position', labelPos, ...                                     
        'Units','normalized', ...                                     
        'BackgroundColor',[0.5 0.5 0.5], ...                          
        'ForegroundColor','white', ...                                
        'String','Noise Power (W)');                                      
                                                                      
    btnPos=[left  yLabelPos-labelHt-btnHt-btnOffset ...               
            0.5*btnWid+frmBorder+0.07  btnHt];                        
    noisepower_field = uicontrol( ...                                      
        'Style','edit', ...                                           
        'Position', btnPos, ...                                       
        'Units','normalized', ...                                     
        'String',' 0.005',...                                             
        'CallBack','ASK(''setnoisepower'')');                             
                                                                      
 %====================================                                
    % The INFO button                                                 
    uicontrol( ...                                                    
        'Style','push', ...                                           
        'Units','normalized', ...                                     
        'Position',[left bottom+(2*labelHt)+spacing btnWid 2*labelHt], ....                                                                   
        'String','Info', ...                                          
        'Callback','ASK(''info'')');                                 
                                                                      
   %========================================                          
   % The CLOSE button                                                 
    done_button=uicontrol('Style','Pushbutton', ...                   
        'Position',[left bottom btnWid 2*labelHt], ...                
        'Units','normalized','Callback', ...                          
        'ASK(''done'')','String','Close');                           
%====================================                              
                                                                      
%=======================================================              
% Create intial signal                                               
amp=10;
highamp=10;
lowamp=1;
noisepower=0.005;                                                          
fsample=20;                                                           
freq=10;    		% hertz                                                    
N=4000;    % number of samples                                      
tb0=0;		% start of a bit period
tb1=0.1;	% end of a bit period
tb=linspace(tb0,tb1,0.1*N);      % create bit vector                                                                  
t0=0;       % seconds                                               
t1=10*tb1;      % seconds                                              
t=linspace(t0,t1,N);                                             
T=(t1-t0)/N;            % sampling rate in seconds
               
high=highamp*sin(freq*tb*2*pi);                        
low=lowamp*sin(freq*tb*2*pi);

% time domain                                                    
val = get(popup,'Value');                                         
if (val == 1),                                                    
        f=[high,high,high,low,low,high,low,high,low,high];  % basic ask 
end;                                                              
                                                                      
                                                                      
ax_ana=axes('Position',[.10 .10 .6 .8]);
analog=plot(t,f,'EraseMode','xor');                               
axis([t0 t1 -1*highamp-.25*highamp 1*highamp+0.25*highamp]);                   
grid;                                                                
ylabel('Volts');                                                       
xlabel('Time (Seconds)');                                             
title('Amplitude Shift Keying Waveform');                                               
                                                                      
ASK_DAT=[noisepower; highamp; lowamp; popup; analog; N; highampf_field; lowamp_field; noisepower_field; t(:); ];                                 
                                                                      
                                                                      
watchoff(oldFigNumber);                                               
                                                                      
%===========================================================          
elseif strcmp(action,'redraw'),                                       
                                                                      
    noisepower=ASK_DAT(1);                                             
    highamp=ASK_DAT(2);                                                
    lowamp=ASK_DAT(3);                                                 
    popup=ASK_DAT(4);                                               
    analog=ASK_DAT(5);                                              
    N=ASK_DAT(6);                                                   
    highampf_field=ASK_DAT(7);                                         
    lowamp_field=ASK_DAT(8);                                         
    noisepower_field=ASK_DAT(9);                                                  
    t=ASK_DAT(10:10+N-1);                                           
	freq=10;    		% hertz  	
	tb0=0;			% start of a bit period
	tb1=0.1;		% end of a bit period
	tb=linspace(tb0,tb1,0.1*N);      % create bit vector                                                                  
	t0=0;       	% seconds                                               
	t1=10*tb1;      	% seconds                                              
	t=linspace(t0,t1,N);                                             
	T=(t1-t0)/N;            % sampling rate in seconds
               
	%generate ASK Signal
	high=highamp*sin(freq*tb*2*pi);                        
	low=lowamp*sin(freq*tb*2*pi);
	
	% generate AWGN
	n0 = sqrt(noisepower)*randn(1,N);  % Scale variance to reflect total noise power in 80 MHz Bandwidth

	val = get(popup,'Value');                                             

	if (val == 1),                                                    
        f=[high,high,high,low,low,high,low,high,low,high];  % basic ask 
    elseif (val == 2),                                                
        tmp=[high,high,high,low,low,high,low,high,low,high];  % ask + noise 
		f = tmp+n0;                                       
    end;                                                              
    set(analog,'YData',f);                                            
    drawnow                                                           
                                                                      
%======================================                               
elseif strcmp(action,'setnoisepower'),                                     
                                                                      
    noisepower_field=ASK_DAT(9);                                          
    xf = str2num(get(noisepower_field,'string'));                          
                                                                      
	if isempty(xf),   % handle the non-numeric case                       
        set(ASK_DAT(9),'string',num2str(ASK_DAT(1)));               
    elseif (xf > 20),                                                   		
        set(ASK_DAT(9),'string',num2str(20));                        
        xf=20;                                                        
        ASK_DAT(1)=xf;                                               
    else                                                              
    ASK_DAT(1)=xf;                                                   
    end;                                                              
                                                                      
    ASK('redraw');                                                   
                                                                      
%======================================                               
elseif strcmp(action,'sethighamp'),                                     
                                                                      
    highampf_field=ASK_DAT(7);                                          
    xf = str2num(get(highampf_field,'string'));                          
                                                                      
if isempty(xf),   % handle the non-numeric case                       
        set(ASK_DAT(7),'string',num2str(ASK_DAT(2)));               
    elseif (xf>10),                                                   
        set(ASK_DAT(7),'string',num2str(10));                        
        xf=10;                                                        
        ASK_DAT(2)=xf;                                               
    else                                                              
    ASK_DAT(2)=xf;                                                   
    end;                                                              
                                                                      
    ASK('redraw');                                                   
                                                                      
%============================================================         
elseif strcmp(action,'setlowamp'),                                     
                                                                      
    lowamp_field=ASK_DAT(8);                                          
    xf = str2num(get(lowamp_field,'String'));                          
                                                                      
if isempty(xf),   % handle the non-numeric case                       
        set(ASK_DAT(8),'string',num2str(ASK_DAT(3)));               
    elseif (xf>10),                                                   
        set(ASK_DAT(8),'string',num2str(10));                        
        xf=10;                                                        
        ASK_DAT(3)=xf;                                               
    else                                                              
    ASK_DAT(3)=xf;                                                   
    end;                                                              
                                                                      
    ASK('redraw');                                                   
                                                                      

%======================================                               
elseif strcmp(action,'done'),                                         
    % close the figure window that is showing the window fnction:     
    % u = get(gcf,'userdata');                                        
%    if (ASK_DAT(8)~=-1),                                            
%        close(ASK_DAT(8));                                          
%    end;                                                              
    close(gcf);                                                       
    clear global ASK_DAT                                             
    clear global ADDIT_DAT                                            
                                                                      
                                                                      
%====================================                                 
elseif strcmp(action,'info'),                                         
    ttlStr = 'Analog vs. Digital Signals';                            
                                                                      
    hlpStr1= ...                                                      
        ['                                                  '];        
                                                                      
                                                                      
    hlpStr2= ...                                                      
        [' 													 '];        
                                                                      
                                                                      
        helpfun(ttlStr, hlpStr1, hlpStr2);                            
end                                                                   
