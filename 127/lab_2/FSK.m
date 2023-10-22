%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
function FSK(action);                                               
% Part 1 ofECTE196 Lab experiment on Signals                                                                      
%       possible actions:                                                   
%         'start'                                                           
%         'redraw'                                                          
%         'done'                                                            
%         'setfreq'
%		 'setamp'
% Modified by Christian Ritz, February 05
                                                                      
if nargin<1,                                                          
   action='start';                                                    
                                                                      
end;                                                                  
                                                                      
global FSK_DAT                                                       
                                                                      
if strcmp(action,'start'),                                            
                                                                      
    %====================================                             
    % Graphics initialization                                         
    oldFigNumber = watchon;                                           
    figNumber = figure;                                               
    set(gcf, ...                                                      
        'NumberTitle','off', ...                                      
        'Name','Part 2 - Frequency Shift Keying', ...                      
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
    btnOffset=0.001;                                                  
    % Spacing between the button and the next command's label         
    spacing=0.005;                                                     
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
                                                                      
popup=uicontrol('Style','Popup','String','fsk|fsk+noise', ...                                                                  
        'Position', btnPos, ...                                       
        'Units','normalized',...                                      
        'CallBack','FSK(''redraw'')');                               
  %====================================                               
  %====================================                               
    % The Noise Power editable text box                                  
    btnNumber=1;                                                      
    yLabelPos=top-(btnNumber)*(btnHt+labelHt+spacing);              
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
        'String',' 0.05',...                                             
        'CallBack','FSK(''setnoisepower'')');                             
%====================================                                
    % The High Frequency editable text box                               
    btnNumber=2;                                                      
    yLabelPos=top-(btnNumber)*(btnHt+labelHt+spacing);         
    labelPos=[left yLabelPos-labelHt labelWid labelHt];               
    lowfreq_text = uicontrol( ...                                        
        'Style','text', ...                                           
        'Position', labelPos, ...                                     
        'Units','normalized', ...                                     
        'BackgroundColor',[0.5 0.5 0.5], ...                          
        'ForegroundColor','white', ...                                
        'String','High Frequency (Hz)');                                      
                                                                      
    btnPos=[left  yLabelPos-labelHt-btnHt-btnOffset ...               
            0.5*btnWid+frmBorder+0.07  btnHt];                        
    highfreqf_field = uicontrol( ...                                      
        'Style','edit', ...                                           
        'Position', btnPos, ...                                       
        'Units','normalized', ...                                     
        'String',' 20',...                                             
        'CallBack','FSK(''sethighfreq'')');                             
  %====================================                               
    % The Low Frequency editable text box                                  
    btnNumber=3;                                                      
    yLabelPos=top-(btnNumber)*(btnHt+labelHt+spacing);              
    labelPos=[left yLabelPos-labelHt labelWid labelHt];               
    lowfreq_text = uicontrol( ...                                        
        'Style','text', ...                                           
        'Position', labelPos, ...                                     
        'Units','normalized', ...                                     
        'BackgroundColor',[0.5 0.5 0.5], ...                          
        'ForegroundColor','white', ...                                
        'String','Low Freq (Hz)');                                      
                                                                      
    btnPos=[left  yLabelPos-labelHt-btnHt-btnOffset ...               
            0.5*btnWid+frmBorder+0.07  btnHt];                        
    lowfreq_field = uicontrol( ...                                      
        'Style','edit', ...                                           
        'Position', btnPos, ...                                       
        'Units','normalized', ...                                     
        'String',' 10',...                                             
        'CallBack','FSK(''setlowfreq'')');                             
                                                                       
 %====================================                                
    % The INFO button                                                 
    uicontrol( ...                                                    
        'Style','push', ...                                           
        'Units','normalized', ...                                     
        'Position',[left bottom+(2*labelHt)+spacing btnWid 2*labelHt], ....                                                                   
        'String','Info', ...                                          
        'Callback','FSK(''info'')');                                 
                                                                      
   %========================================                          
   % The CLOSE button                                                 
    done_button=uicontrol('Style','Pushbutton', ...                   
        'Position',[left bottom btnWid 2*labelHt], ...                
        'Units','normalized','Callback', ...                          
        'FSK(''done'')','String','Close');                           
%====================================                              
                                                                      
%=======================================================              
% Create intial signal                                               
amp=5;
highfreq=20;
lowfreq=10;
noisepower=0.05;
freq=10;    		% hertz                                                    
N=4000;    % number of samples                                      
tb0=0;		% start of a bit period
tb1=0.1;	% end of a bit period
tb=linspace(tb0,tb1,0.1*N);      % create bit vector                                                                  
t0=0;       % seconds                                               
t1=10*tb1;      % seconds                                              
t=linspace(t0,t1,N);                                             
T=(t1-t0)/N;            % sampling rate in seconds

high=amp*sin(highfreq*tb*2*pi);                        
low=amp*sin(lowfreq*tb*2*pi);

% time domain                                                    
val = get(popup,'Value');                                         
if (val == 1),                                                    
        f=[high,high,high,low,low,high,low,high,low,high];  % basic fsk 
end;                                                              
                                                                      
                                                                      
ax_ana=axes('Position',[.10 .10 .6 .8]);
analog=plot(t,f,'EraseMode','xor');                               
axis([t0 t1 -1*amp-.25*amp 1*amp+0.25*amp]);                   
grid;                                                                
ylabel('Volts');                                                       
xlabel('Time (Seconds)');                                             
title('Frequency Shift Keying Waveform');                                               
                                                                      
FSK_DAT=[noisepower; highfreq; lowfreq; popup; analog; N; highfreqf_field; lowfreq_field; noisepower_field; t(:); ];                                 
                                                                      
                                                                      
watchoff(oldFigNumber);                                               
                                                                      
%===========================================================          
elseif strcmp(action,'redraw'),                                       
                                                                      
    noisepower=FSK_DAT(1);                                             
    highfreq=FSK_DAT(2);                                                
    lowfreq=FSK_DAT(3);                                                 
    popup=FSK_DAT(4);                                               
    analog=FSK_DAT(5);                                              
    N=FSK_DAT(6);                                                   
    highfreqf_field=FSK_DAT(7);                                         
    lowfreq_field=FSK_DAT(8);
    noisepower_field=FSK_DAT(9);	                                         
    t=FSK_DAT(10:10+N-1);                                           

%	Generate The AWGN
	% Noise Power is Desired variance of noise process
	n0 = sqrt(noisepower)*randn(1,N);  % Scale variance to reflect total noise power in 80 MHz Bandwidth

%	Generate the 1 and 0 signals
		
	amp=5;    		% Volts  	
	tb0=0;			% start of a bit period
	tb1=0.1;		% end of a bit period
	tb=linspace(tb0,tb1,0.1*N);      % create bit vector                                                                  
	t0=0;       	% seconds                                               
	t1=10*tb1;      	% seconds                                              
	
	%t=linspace(t0,t1,N);                                             
	%T=(t1-t0)/N;            % sampling rate in seconds
               
	high=amp*sin(highfreq*tb*2*pi);                        
	low=amp*sin(lowfreq*tb*2*pi);
	val = get(popup,'Value');  
	                                           
	if (val == 1),                                                    
        f=[high,high,high,low,low,high,low,high,low,high];  % basic fsk 
    elseif (val == 2),                                                
        tmp=[high,high,high,low,low,high,low,high,low,high];  % fsk + noise 
		f = tmp+n0;                                       
    end;                                                              
    set(analog,'YData',f);                                            
    drawnow                                                           
                                                                      
%======================================                               
elseif strcmp(action,'sethighfreq'),                                     
                                                                      
    highfreqf_field=FSK_DAT(7);                                          
    xf = str2num(get(highfreqf_field,'string'));                          
                                                                      
	if isempty(xf),   % handle the non-numeric case                       
        set(FSK_DAT(7),'string',num2str(FSK_DAT(2)));               
    elseif (xf >20),                                                   		
        set(FSK_DAT(7),'string',num2str(20));                        
        xf=20;                                                        
        FSK_DAT(2)=xf;                                               
    else                                                              
    FSK_DAT(2)=xf;                                                   
    end;                                                              
                                                                      
    FSK('redraw');                                                   
                                                                      
%======================================                               
elseif strcmp(action,'setnoisepower'),                                     
                                                                      
    noisepower_field=FSK_DAT(9);                                          
    xf = str2num(get(noisepower_field,'string'));                          
                                                                      
	if isempty(xf),   % handle the non-numeric case                       
        set(FSK_DAT(9),'string',num2str(FSK_DAT(1)));               
    elseif (xf >20),                                                   		
        set(FSK_DAT(9),'string',num2str(20));                        
        xf=20;                                                        
        FSK_DAT(1)=xf;                                               
    else                                                              
    FSK_DAT(1)=xf;                                                   
    end;                                                              
                                                                      
    FSK('redraw');                                                   
                                                                      
%============================================================         
elseif strcmp(action,'setlowfreq'),                                     
                                                                      
    lowfreq_field=FSK_DAT(8);                                          
    xf = str2num(get(lowfreq_field,'String'));                          
                                                                      
if isempty(xf),   % handle the non-numeric case                       
        set(FSK_DAT(8),'string',num2str(FSK_DAT(3)));               
    elseif (xf>10),                                                   
        set(FSK_DAT(8),'string',num2str(10));                        
        xf=10;                                                        
        FSK_DAT(3)=xf;                                               
    else                                                              
    FSK_DAT(3)=xf;                                                   
    end;                                                              
                                                                      
    FSK('redraw');                                                   
                                                                      
%======================================                               
elseif strcmp(action,'done'),                                         
    % close the figure window that is showing the window fnction:     
    % u = get(gcf,'userdata');                                        
%    if (FSK_DAT(8)~=-1),                                            
%        close(FSK_DAT(8));                                          
%    end;                                                              
    close(gcf);                                                       
    clear global FSK_DAT                                             
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
